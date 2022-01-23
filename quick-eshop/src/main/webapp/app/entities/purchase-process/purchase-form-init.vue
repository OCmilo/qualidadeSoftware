<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.purchaseForm.home.createOrEditLabel"
          data-cy="PurchaseFormCreateUpdateHeading"
          v-text="$t('quickeshopApp.purchaseForm.home.createOrEditLabel')"
        >
          Create or edit a PurchaseForm
        </h2>
        <div v-if="purchaseProcess.processInstance">
          <akip-show-process-definition :processDefinition="purchaseProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="purchaseProcess.product">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseForm.name')" for="purchase-form-name">Name</label>
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="purchase-form-name"
                    data-cy="name"
                    :class="{ valid: !$v.purchaseProcess.product.name.$invalid, invalid: $v.purchaseProcess.product.name.$invalid }"
                    v-model="$v.purchaseProcess.product.name.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseForm.quantity')" for="purchase-form-quantity"
                    >Quantity</label
                  >
                  <input
                    type="number"
                    class="form-control"
                    name="quantity"
                    id="purchase-form-quantity"
                    data-cy="quantity"
                    :class="{ valid: !$v.purchaseProcess.product.quantity.$invalid, invalid: $v.purchaseProcess.product.quantity.$invalid }"
                    v-model.number="$v.purchaseProcess.product.quantity.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.purchaseProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./purchase-form-init.component.ts"></script>
