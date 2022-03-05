<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.coupomForm.home.createOrEditLabel"
          data-cy="CoupomFormCreateUpdateHeading"
          v-text="$t('quickeshopApp.coupomForm.home.createOrEditLabel')"
        >
          Create or edit a CoupomForm
        </h2>
        <div>
          <div class="form-group" v-if="coupomForm.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="coupomForm.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.coupomForm.nome')" for="coupom-form-nome">Nome</label>
            <input
              type="text"
              class="form-control"
              name="nome"
              id="coupom-form-nome"
              data-cy="nome"
              :class="{ valid: !$v.coupomForm.nome.$invalid, invalid: $v.coupomForm.nome.$invalid }"
              v-model="$v.coupomForm.nome.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.coupomForm.desconto')" for="coupom-form-desconto">Desconto</label>
            <input
              type="number"
              class="form-control"
              name="desconto"
              id="coupom-form-desconto"
              data-cy="desconto"
              :class="{ valid: !$v.coupomForm.desconto.$invalid, invalid: $v.coupomForm.desconto.$invalid }"
              v-model.number="$v.coupomForm.desconto.$model"
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
            :disabled="$v.coupomForm.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./coupom-form-update.component.ts"></script>
