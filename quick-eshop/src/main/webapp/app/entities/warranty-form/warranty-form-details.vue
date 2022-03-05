<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="warrantyForm">
        <h2 class="jh-entity-heading" data-cy="warrantyFormDetailsHeading">
          <span v-text="$t('quickeshopApp.warrantyForm.detail.title')">WarrantyForm</span> {{ warrantyForm.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.warrantyForm.name')">Name</span>
          </dt>
          <dd>
            <span>{{ warrantyForm.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.warrantyForm.quantity')">Quantity</span>
          </dt>
          <dd>
            <span>{{ warrantyForm.quantity }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="warrantyForm.id"
          :to="{ name: 'WarrantyFormEdit', params: { warrantyFormId: warrantyForm.id } }"
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

<script lang="ts" src="./warranty-form-details.component.ts"></script>
