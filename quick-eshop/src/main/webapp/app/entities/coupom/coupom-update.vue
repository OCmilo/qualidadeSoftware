<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="quickeshopApp.coupom.home.createOrEditLabel"
          data-cy="CoupomCreateUpdateHeading"
          v-text="$t('quickeshopApp.coupom.home.createOrEditLabel')"
        >
          Create or edit a Coupom
        </h2>
        <div>
          <div class="form-group" v-if="coupom.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="coupom.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.coupom.nome')" for="coupom-nome">Nome</label>
            <input
              type="text"
              class="form-control"
              name="nome"
              id="coupom-nome"
              data-cy="nome"
              :class="{ valid: !$v.coupom.nome.$invalid, invalid: $v.coupom.nome.$invalid }"
              v-model="$v.coupom.nome.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('quickeshopApp.coupom.desconto')" for="coupom-desconto">Desconto</label>
            <input
              type="number"
              class="form-control"
              name="desconto"
              id="coupom-desconto"
              data-cy="desconto"
              :class="{ valid: !$v.coupom.desconto.$invalid, invalid: $v.coupom.desconto.$invalid }"
              v-model.number="$v.coupom.desconto.$model"
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
            :disabled="$v.coupom.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./coupom-update.component.ts"></script>
