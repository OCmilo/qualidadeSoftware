<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="appApp.freight.home.createOrEditLabel"
          data-cy="FreightCreateUpdateHeading"
          v-text="$t('appApp.freight.home.createOrEditLabel')"
        >
          Create or edit a Freight
        </h2>
        <div>
          <div class="form-group" v-if="freight.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="freight.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.freight.freighter')" for="freight-freighter">Freighter</label>
            <input
              type="text"
              class="form-control"
              name="freighter"
              id="freight-freighter"
              data-cy="freighter"
              :class="{ valid: !$v.freight.freighter.$invalid, invalid: $v.freight.freighter.$invalid }"
              v-model="$v.freight.freighter.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('appApp.freight.freightPrice')" for="freight-freightPrice">Freight Price</label>
            <input
              type="number"
              class="form-control"
              name="freightPrice"
              id="freight-freightPrice"
              data-cy="freightPrice"
              :class="{ valid: !$v.freight.freightPrice.$invalid, invalid: $v.freight.freightPrice.$invalid }"
              v-model.number="$v.freight.freightPrice.$model"
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
            :disabled="$v.freight.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./freight-update.component.ts"></script>
