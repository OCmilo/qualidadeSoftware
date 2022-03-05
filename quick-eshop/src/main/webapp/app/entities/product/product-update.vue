<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="appApp.product.home.createOrEditLabel"
          data-cy="ProductCreateUpdateHeading"
          v-text="$t('appApp.product.home.createOrEditLabel')"
        >
          Create or edit a Product
        </h2>
        <div>
          <div class="form-group" v-if="product.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="product.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.product.productName')" for="product-productName">Product Name</label>
            <input
              type="text"
              class="form-control"
              name="productName"
              id="product-productName"
              data-cy="productName"
              :class="{ valid: !$v.product.productName.$invalid, invalid: $v.product.productName.$invalid }"
              v-model="$v.product.productName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.product.availableQuantity')" for="product-availableQuantity"
              >Available Quantity</label
            >
            <input
              type="number"
              class="form-control"
              name="availableQuantity"
              id="product-availableQuantity"
              data-cy="availableQuantity"
              :class="{ valid: !$v.product.availableQuantity.$invalid, invalid: $v.product.availableQuantity.$invalid }"
              v-model.number="$v.product.availableQuantity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.product.price')" for="product-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="product-price"
              data-cy="price"
              :class="{ valid: !$v.product.price.$invalid, invalid: $v.product.price.$invalid }"
              v-model.number="$v.product.price.$model"
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
            :disabled="$v.product.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-update.component.ts"></script>
