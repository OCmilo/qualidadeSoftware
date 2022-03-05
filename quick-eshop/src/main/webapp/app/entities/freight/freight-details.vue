<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="freight">
        <h2 class="jh-entity-heading" data-cy="freightDetailsHeading">
          <span v-text="$t('quickeshopApp.freight.detail.title')">Freight</span> {{ freight.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.freight.empresa')">Empresa</span>
          </dt>
          <dd>
            <span>{{ freight.empresa }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.freight.valor')">Valor</span>
          </dt>
          <dd>
            <span>{{ freight.valor }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="freight.id" :to="{ name: 'FreightEdit', params: { freightId: freight.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./freight-details.component.ts"></script>
