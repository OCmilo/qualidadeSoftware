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
            <label class="form-control-label" v-text="$t('appApp.product.barcode')" for="product-barcode">Barcode</label>
            <input
              type="text"
              class="form-control"
              name="barcode"
              id="product-barcode"
              data-cy="barcode"
              :class="{ valid: !$v.product.barcode.$invalid, invalid: $v.product.barcode.$invalid }"
              v-model="$v.product.barcode.$model"
            />
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
