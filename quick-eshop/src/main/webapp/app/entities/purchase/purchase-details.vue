<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="purchase">
        <h2 class="jh-entity-heading" data-cy="purchaseDetailsHeading">
          <span v-text="$t('appApp.purchase.detail.title')">Purchase</span> {{ purchase.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('appApp.purchase.userName')">User Name</span>
          </dt>
          <dd>
            <span>{{ purchase.userName }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.userEmail')">User Email</span>
          </dt>
          <dd>
            <span>{{ purchase.userEmail }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.address')">Address</span>
          </dt>
          <dd>
            <span>{{ purchase.address }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.quantity')">Quantity</span>
          </dt>
          <dd>
            <span>{{ purchase.quantity }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.confirmation')">Confirmation</span>
          </dt>
          <dd>
            <span>{{ purchase.confirmation }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.withWarranty')">With Warranty</span>
          </dt>
          <dd>
            <span>{{ purchase.withWarranty }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.withCoupon')">With Coupon</span>
          </dt>
          <dd>
            <span>{{ purchase.withCoupon }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.coupon')">Coupon</span>
          </dt>
          <dd>
            <div v-if="purchase.coupon">
              <router-link :to="{ name: 'CouponView', params: { couponId: purchase.coupon.id } }">{{
                purchase.coupon.couponName
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.freight')">Freight</span>
          </dt>
          <dd>
            <div v-if="purchase.freight">
              <router-link :to="{ name: 'FreightView', params: { freightId: purchase.freight.id } }">{{
                purchase.freight.freighter
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.product')">Product</span>
          </dt>
          <dd>
            <div v-if="purchase.product">
              <router-link :to="{ name: 'ProductView', params: { productId: purchase.product.id } }">{{
                purchase.product.productName
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('appApp.purchase.warranty')">Warranty</span>
          </dt>
          <dd>
            <div v-if="purchase.warranty">
              <router-link :to="{ name: 'WarrantyView', params: { warrantyId: purchase.warranty.id } }">{{
                purchase.warranty.warrantyDescription
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./purchase-details.component.ts"></script>
