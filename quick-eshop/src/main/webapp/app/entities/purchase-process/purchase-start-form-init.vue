<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="appApp.purchaseStartForm.home.createOrEditLabel"
          data-cy="PurchaseStartFormCreateUpdateHeading"
          v-text="$t('appApp.purchaseStartForm.home.createOrEditLabel')"
        >
          Create or edit a PurchaseStartForm
        </h2>
        <div v-if="purchaseProcess.processInstance">
          <akip-show-process-definition :processDefinition="purchaseProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="purchaseProcess.purchase">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('appApp.purchaseStartForm.userName')" for="purchase-start-form-userName"
                    >User Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userName"
                    id="purchase-start-form-userName"
                    data-cy="userName"
                    :class="{
                      valid: !$v.purchaseProcess.purchase.userName.$invalid,
                      invalid: $v.purchaseProcess.purchase.userName.$invalid,
                    }"
                    v-model="$v.purchaseProcess.purchase.userName.$model"
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
<script lang="ts" src="./purchase-start-form-init.component.ts"></script>
