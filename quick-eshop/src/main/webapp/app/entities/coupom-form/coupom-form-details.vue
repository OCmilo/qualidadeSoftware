<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="coupomForm">
        <h2 class="jh-entity-heading" data-cy="coupomFormDetailsHeading">
          <span v-text="$t('quickeshopApp.coupomForm.detail.title')">CoupomForm</span> {{ coupomForm.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.coupomForm.nome')">Nome</span>
          </dt>
          <dd>
            <span>{{ coupomForm.nome }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.coupomForm.desconto')">Desconto</span>
          </dt>
          <dd>
            <span>{{ coupomForm.desconto }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="coupomForm.id"
          :to="{ name: 'CoupomFormEdit', params: { coupomFormId: coupomForm.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./coupom-form-details.component.ts"></script>
