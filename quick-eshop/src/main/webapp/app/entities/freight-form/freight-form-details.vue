<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="freightForm">
        <h2 class="jh-entity-heading" data-cy="freightFormDetailsHeading">
          <span v-text="$t('quickeshopApp.freightForm.detail.title')">FreightForm</span> {{ freightForm.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.freightForm.name')">Name</span>
          </dt>
          <dd>
            <span>{{ freightForm.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.freightForm.quantity')">Quantity</span>
          </dt>
          <dd>
            <span>{{ freightForm.quantity }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="freightForm.id"
          :to="{ name: 'FreightFormEdit', params: { freightFormId: freightForm.id } }"
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

<script lang="ts" src="./freight-form-details.component.ts"></script>
