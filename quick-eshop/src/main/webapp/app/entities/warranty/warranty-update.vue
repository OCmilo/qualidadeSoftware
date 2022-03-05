<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="appApp.warranty.home.createOrEditLabel"
          data-cy="WarrantyCreateUpdateHeading"
          v-text="$t('appApp.warranty.home.createOrEditLabel')"
        >
          Create or edit a Warranty
        </h2>
        <div>
          <div class="form-group" v-if="warranty.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="warranty.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.warranty.warrantyDesc')" for="warranty-warrantyDesc">Warranty Desc</label>
            <input
              type="text"
              class="form-control"
              name="warrantyDesc"
              id="warranty-warrantyDesc"
              data-cy="warrantyDesc"
              :class="{ valid: !$v.warranty.warrantyDesc.$invalid, invalid: $v.warranty.warrantyDesc.$invalid }"
              v-model="$v.warranty.warrantyDesc.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.warranty.warrantyMonths')" for="warranty-warrantyMonths"
              >Warranty Months</label
            >
            <input
              type="number"
              class="form-control"
              name="warrantyMonths"
              id="warranty-warrantyMonths"
              data-cy="warrantyMonths"
              :class="{ valid: !$v.warranty.warrantyMonths.$invalid, invalid: $v.warranty.warrantyMonths.$invalid }"
              v-model.number="$v.warranty.warrantyMonths.$model"
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
            :disabled="$v.warranty.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./warranty-update.component.ts"></script>
