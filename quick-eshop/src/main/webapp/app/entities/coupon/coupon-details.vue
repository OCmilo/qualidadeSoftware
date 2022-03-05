<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="coupon">
        <h2 class="jh-entity-heading" data-cy="couponDetailsHeading">
          <span v-text="$t('appApp.coupon.detail.title')">Coupon</span> {{ coupon.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('appApp.coupon.couponName')">Coupon Name</span>
          </dt>
          <dd>
            <span>{{ coupon.couponName }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.coupon.couponDiscount')">Coupon Discount</span>
          </dt>
          <dd>
            <span>{{ coupon.couponDiscount }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="coupon.id" :to="{ name: 'CouponEdit', params: { couponId: coupon.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./coupon-details.component.ts"></script>
