<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.freight.home.createOrEditLabel"
          data-cy="FreightCreateUpdateHeading"
          v-text="$t('quickeshopApp.freight.home.createOrEditLabel')"
        >
          Create or edit a Freight
        </h2>
        <div>
          <div class="form-group" v-if="freight.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="freight.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.freight.empresa')" for="freight-empresa">Empresa</label>
            <input
              type="text"
              class="form-control"
              name="empresa"
              id="freight-empresa"
              data-cy="empresa"
              :class="{ valid: !$v.freight.empresa.$invalid, invalid: $v.freight.empresa.$invalid }"
              v-model="$v.freight.empresa.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.freight.valor')" for="freight-valor">Valor</label>
            <input
              type="number"
              class="form-control"
              name="valor"
              id="freight-valor"
              data-cy="valor"
              :class="{ valid: !$v.freight.valor.$invalid, invalid: $v.freight.valor.$invalid }"
              v-model.number="$v.freight.valor.$model"
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
