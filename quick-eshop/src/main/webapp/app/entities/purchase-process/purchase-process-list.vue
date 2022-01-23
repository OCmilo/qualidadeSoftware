<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="purchaseProcessDetailsHeading">
      <span v-text="$t('quickeshopApp.purchaseProcess.home.title')">PurchaseProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('quickeshopApp.purchaseProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/PurchaseProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('quickeshopApp.purchaseProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && purchaseProcessList && purchaseProcessList.length === 0">
      <span v-text="$t('quickeshopApp.purchaseProcess.home.notFound')">No purchaseProcess found</span>
    </div>
    <div class="table-responsive" v-if="purchaseProcessList && purchaseProcessList.length > 0">
      <table class="table table-striped" aria-describedby="purchaseProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Product</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="purchaseProcess in purchaseProcessList" :key="purchaseProcess.id" data-cy="entityTable">
            <td>{{ purchaseProcess.id }}</td>
            <td>
              <div v-if="purchaseProcess.processInstance">
                <router-link :to="`/process-definition/PurchaseProcess/instance/${purchaseProcess.processInstance.id}/view`">
                  {{ purchaseProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="purchaseProcess.product">
                <router-link :to="{ name: 'ProductView', params: { productId: purchaseProcess.product.id } }">{{
                  purchaseProcess.product.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="purchaseProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{ purchaseProcess.processInstance.startDate ? $d(Date.parse(purchaseProcess.processInstance.startDate), 'short') : '' }}
            </td>
            <td>{{ purchaseProcess.processInstance.endDate ? $d(Date.parse(purchaseProcess.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/PurchaseProcess/instance/${purchaseProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./purchase-process-list.component.ts"></script>
