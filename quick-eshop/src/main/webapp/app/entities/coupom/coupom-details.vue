<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="coupom">
        <h2 class="jh-entity-heading" data-cy="coupomDetailsHeading">
          <span v-text="$t('quickeshopApp.coupom.detail.title')">Coupom</span> {{ coupom.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.coupom.nome')">Nome</span>
          </dt>
          <dd>
            <span>{{ coupom.nome }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.coupom.desconto')">Desconto</span>
          </dt>
          <dd>
            <span>{{ coupom.desconto }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="coupom.id" :to="{ name: 'CoupomEdit', params: { coupomId: coupom.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./coupom-details.component.ts"></script>
