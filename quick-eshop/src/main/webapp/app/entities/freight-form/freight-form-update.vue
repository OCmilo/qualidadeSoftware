<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.freightForm.home.createOrEditLabel"
          data-cy="FreightFormCreateUpdateHeading"
          v-text="$t('quickeshopApp.freightForm.home.createOrEditLabel')"
        >
          Create or edit a FreightForm
        </h2>
        <div>
          <div class="form-group" v-if="freightForm.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="freightForm.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.freightForm.name')" for="freight-form-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="freight-form-name"
              data-cy="name"
              :class="{ valid: !$v.freightForm.name.$invalid, invalid: $v.freightForm.name.$invalid }"
              v-model="$v.freightForm.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.freightForm.quantity')" for="freight-form-quantity">Quantity</label>
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="freight-form-quantity"
              data-cy="quantity"
              :class="{ valid: !$v.freightForm.quantity.$invalid, invalid: $v.freightForm.quantity.$invalid }"
              v-model.number="$v.freightForm.quantity.$model"
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
            :disabled="$v.freightForm.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./freight-form-update.component.ts"></script>
