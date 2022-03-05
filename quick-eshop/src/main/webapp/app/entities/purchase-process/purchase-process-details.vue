<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="purchaseProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="purchaseProcessDetailsHeading">
          <span v-text="$t('appApp.purchaseProcess.detail.title')">PurchaseProcess</span> {{ purchaseProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="purchaseProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('appApp.purchaseProcess.detail.title')">PurchaseProcess</span> #{{ purchaseProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="purchaseProcess.purchase">
              <h5 class="card-header">
                <span class="title"> Purchase #{{ purchaseProcess.purchase.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.userName')">userName</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userName"
                    id="purchase-userName"
                    data-cy="userName"
                    v-model="purchaseProcess.purchase.userName"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.userEmail')">userEmail</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="purchase-userEmail"
                    data-cy="userEmail"
                    v-model="purchaseProcess.purchase.userEmail"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.address')">address</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="address"
                    id="purchase-address"
                    data-cy="address"
                    v-model="purchaseProcess.purchase.address"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.quantity')">quantity</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="quantity"
                    id="purchase-quantity"
                    data-cy="quantity"
                    v-model="purchaseProcess.purchase.quantity"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.confirmation')">confirmation</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="confirmation"
                    id="purchase-confirmation"
                    data-cy="confirmation"
                    v-model="purchaseProcess.purchase.confirmation"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.withWarranty')">withWarranty</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="withWarranty"
                    id="purchase-withWarranty"
                    data-cy="withWarranty"
                    v-model="purchaseProcess.purchase.withWarranty"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.withCoupon')">withCoupon</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="withCoupon"
                    id="purchase-withCoupon"
                    data-cy="withCoupon"
                    v-model="purchaseProcess.purchase.withCoupon"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.coupon')" for="purchase-process-coupon"
                    >Coupon</label
                  >
                  <input
                    v-if="purchaseProcess.purchase.coupon"
                    readonly
                    type="text"
                    class="form-control"
                    name="coupon"
                    id="purchase-coupon"
                    data-cy="coupon"
                    :value="purchaseProcess.purchase.coupon.couponName"
                  />
                  <input v-else readonly type="text" class="form-control" name="coupon" id="purchase-coupon" data-cy="coupon" value="" />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.freight')" for="purchase-process-freight"
                    >Freight</label
                  >
                  <input
                    v-if="purchaseProcess.purchase.freight"
                    readonly
                    type="text"
                    class="form-control"
                    name="freight"
                    id="purchase-freight"
                    data-cy="freight"
                    :value="purchaseProcess.purchase.freight.freighter"
                  />
                  <input v-else readonly type="text" class="form-control" name="freight" id="purchase-freight" data-cy="freight" value="" />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.product')" for="purchase-process-product"
                    >Product</label
                  >
                  <input
                    v-if="purchaseProcess.purchase.product"
                    readonly
                    type="text"
                    class="form-control"
                    name="product"
                    id="purchase-product"
                    data-cy="product"
                    :value="purchaseProcess.purchase.product.productName"
                  />
                  <input v-else readonly type="text" class="form-control" name="product" id="purchase-product" data-cy="product" value="" />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseProcess.warranty')" for="purchase-process-warranty"
                    >Warranty</label
                  >
                  <input
                    v-if="purchaseProcess.purchase.warranty"
                    readonly
                    type="text"
                    class="form-control"
                    name="warranty"
                    id="purchase-warranty"
                    data-cy="warranty"
                    :value="purchaseProcess.purchase.warranty.warrantyDescription"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="warranty"
                    id="purchase-warranty"
                    data-cy="warranty"
                    value=""
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./purchase-process-details.component.ts"></script>
