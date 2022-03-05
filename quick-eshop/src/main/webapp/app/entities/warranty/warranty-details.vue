<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="warranty">
        <h2 class="jh-entity-heading" data-cy="warrantyDetailsHeading">
          <span v-text="$t('appApp.warranty.detail.title')">Warranty</span> {{ warranty.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('appApp.warranty.warrantyDescription')">Warranty Description</span>
          </dt>
          <dd>
            <span>{{ warranty.warrantyDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('appApp.warranty.warrantyMonths')">Warranty Months</span>
          </dt>
          <dd>
            <span>{{ warranty.warrantyMonths }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="warranty.id" :to="{ name: 'WarrantyEdit', params: { warrantyId: warranty.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./warranty-details.component.ts"></script>
