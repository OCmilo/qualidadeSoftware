<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.warrantyForm.home.createOrEditLabel"
          data-cy="WarrantyFormCreateUpdateHeading"
          v-text="$t('quickeshopApp.warrantyForm.home.createOrEditLabel')"
        >
          Create or edit a WarrantyForm
        </h2>
        <div>
          <div class="form-group" v-if="warrantyForm.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="warrantyForm.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.warrantyForm.name')" for="warranty-form-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="warranty-form-name"
              data-cy="name"
              :class="{ valid: !$v.warrantyForm.name.$invalid, invalid: $v.warrantyForm.name.$invalid }"
              v-model="$v.warrantyForm.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.warrantyForm.quantity')" for="warranty-form-quantity"
              >Quantity</label
            >
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="warranty-form-quantity"
              data-cy="quantity"
              :class="{ valid: !$v.warrantyForm.quantity.$invalid, invalid: $v.warrantyForm.quantity.$invalid }"
              v-model.number="$v.warrantyForm.quantity.$model"
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
            :disabled="$v.warrantyForm.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./warranty-form-update.component.ts"></script>
