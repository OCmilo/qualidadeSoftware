<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('appApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.userAddress')">userAddress</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="userAddress"
                id="purchase-userAddress"
                data-cy="userAddress"
                v-model="taskContext.purchaseProcess.purchase.userAddress"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.withCoupon')">withCoupon</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="withCoupon"
                id="purchase-withCoupon"
                data-cy="withCoupon"
                v-model="taskContext.purchaseProcess.purchase.withCoupon"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.freight')" for="choose-freight-freight">Freight</label>
              <input
                v-if="taskContext.purchaseProcess.purchase.freight"
                readonly
                type="text"
                class="form-control"
                name="freight"
                id="purchase-freight"
                data-cy="freight"
                :value="taskContext.purchaseProcess.purchase.freight.freighter"
              />
              <input v-else readonly type="text" class="form-control" name="freight" id="purchase-freight" data-cy="freight" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/PurchaseProcess/task/ChooseFreight/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./choose-freight-details.component.ts"></script>
