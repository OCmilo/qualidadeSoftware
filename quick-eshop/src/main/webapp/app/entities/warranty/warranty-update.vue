<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.warranty.home.createOrEditLabel"
          data-cy="WarrantyCreateUpdateHeading"
          v-text="$t('quickeshopApp.warranty.home.createOrEditLabel')"
        >
          Create or edit a Warranty
        </h2>
        <div>
          <div class="form-group" v-if="warranty.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="warranty.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.warranty.tempo')" for="warranty-tempo">Tempo</label>
            <input
              type="text"
              class="form-control"
              name="tempo"
              id="warranty-tempo"
              data-cy="tempo"
              :class="{ valid: !$v.warranty.tempo.$invalid, invalid: $v.warranty.tempo.$invalid }"
              v-model="$v.warranty.tempo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.warranty.valor')" for="warranty-valor">Valor</label>
            <input
              type="number"
              class="form-control"
              name="valor"
              id="warranty-valor"
              data-cy="valor"
              :class="{ valid: !$v.warranty.valor.$invalid, invalid: $v.warranty.valor.$invalid }"
              v-model.number="$v.warranty.valor.$model"
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
