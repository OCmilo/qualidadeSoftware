<template>
  <div>
    <h2 id="page-heading" data-cy="PurchaseHeading">
      <span v-text="$t('appApp.purchase.home.title')" id="purchase-heading">Purchases</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('appApp.purchase.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && purchases && purchases.length === 0">
      <span v-text="$t('appApp.purchase.home.notFound')">No purchases found</span>
    </div>
    <div class="table-responsive" v-if="purchases && purchases.length > 0">
      <table class="table table-striped" aria-describedby="purchases">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.userName')">User Name</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.userAddress')">User Address</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.quantity')">Quantity</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.confirmacao')">Confirmacao</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.coupon')">Coupon</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.freight')">Freight</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.product')">Product</span></th>
            <th scope="row"><span v-text="$t('appApp.purchase.warranty')">Warranty</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="purchase in purchases" :key="purchase.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PurchaseView', params: { purchaseId: purchase.id } }">{{ purchase.id }}</router-link>
            </td>
            <td>{{ purchase.userName }}</td>
            <td>{{ purchase.userAddress }}</td>
            <td>{{ purchase.quantity }}</td>
            <td>{{ purchase.confirmacao }}</td>
            <td>
              <div v-if="purchase.coupon">
                <router-link :to="{ name: 'CouponView', params: { couponId: purchase.coupon.id } }">{{
                  purchase.coupon.couponName
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="purchase.freight">
                <router-link :to="{ name: 'FreightView', params: { freightId: purchase.freight.id } }">{{
                  purchase.freight.freighter
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="purchase.product">
                <router-link :to="{ name: 'ProductView', params: { productId: purchase.product.id } }">{{
                  purchase.product.productName
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="purchase.warranty">
                <router-link :to="{ name: 'WarrantyView', params: { warrantyId: purchase.warranty.id } }">{{
                  purchase.warranty.warrantyDesc
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PurchaseView', params: { purchaseId: purchase.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="appApp.purchase.delete.question" data-cy="purchaseDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-purchase-heading" v-text="$t('appApp.purchase.delete.question', { id: removeId })">
          Are you sure you want to delete this Purchase?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-purchase"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removePurchase()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./purchase.component.ts"></script>
