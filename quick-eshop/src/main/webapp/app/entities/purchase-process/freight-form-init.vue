<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.freightForm.home.createOrEditLabel"
          data-cy="FreightFormCreateUpdateHeading"
          v-text="$t('quickeshopApp.freightForm.home.createOrEditLabel')"
        >
          Create or edit a FreightForm
        </h2>
        <div v-if="purchaseProcess.processInstance">
          <akip-show-process-definition :processDefinition="purchaseProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="purchaseProcess.freight">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.freightForm.name')" for="freight-form-name">Name</label>
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="freight-form-name"
                    data-cy="name"
                    :class="{ valid: !$v.purchaseProcess.freight.name.$invalid, invalid: $v.purchaseProcess.freight.name.$invalid }"
                    v-model="$v.purchaseProcess.freight.name.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.freightForm.quantity')" for="freight-form-quantity"
                    >Quantity</label
                  >
                  <input
                    type="number"
                    class="form-control"
                    name="quantity"
                    id="freight-form-quantity"
                    data-cy="quantity"
                    :class="{ valid: !$v.purchaseProcess.freight.quantity.$invalid, invalid: $v.purchaseProcess.freight.quantity.$invalid }"
                    v-model.number="$v.purchaseProcess.freight.quantity.$model"
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
<script lang="ts" src="./freight-form-init.component.ts"></script>
