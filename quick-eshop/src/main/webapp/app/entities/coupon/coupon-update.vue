<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="appApp.coupon.home.createOrEditLabel"
          data-cy="CouponCreateUpdateHeading"
          v-text="$t('appApp.coupon.home.createOrEditLabel')"
        >
          Create or edit a Coupon
        </h2>
        <div>
          <div class="form-group" v-if="coupon.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="coupon.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.coupon.couponName')" for="coupon-couponName">Coupon Name</label>
            <input
              type="text"
              class="form-control"
              name="couponName"
              id="coupon-couponName"
              data-cy="couponName"
              :class="{ valid: !$v.coupon.couponName.$invalid, invalid: $v.coupon.couponName.$invalid }"
              v-model="$v.coupon.couponName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.coupon.couponValue')" for="coupon-couponValue">Coupon Value</label>
            <input
              type="number"
              class="form-control"
              name="couponValue"
              id="coupon-couponValue"
              data-cy="couponValue"
              :class="{ valid: !$v.coupon.couponValue.$invalid, invalid: $v.coupon.couponValue.$invalid }"
              v-model.number="$v.coupon.couponValue.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.coupon.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./coupon-update.component.ts"></script>
