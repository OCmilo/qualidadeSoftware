<template>
  <div>
    <h2 id="page-heading" data-cy="WarrantyHeading">
      <span v-text="$t('appApp.warranty.home.title')" id="warranty-heading">Warranties</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('appApp.warranty.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'WarrantyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-warranty"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('appApp.warranty.home.createLabel')"> Create a new Warranty </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && warranties && warranties.length === 0">
      <span v-text="$t('appApp.warranty.home.notFound')">No warranties found</span>
    </div>
    <div class="table-responsive" v-if="warranties && warranties.length > 0">
      <table class="table table-striped" aria-describedby="warranties">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('appApp.warranty.warrantyDesc')">Warranty Desc</span></th>
            <th scope="row"><span v-text="$t('appApp.warranty.warrantyMonths')">Warranty Months</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="warranty in warranties" :key="warranty.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WarrantyView', params: { warrantyId: warranty.id } }">{{ warranty.id }}</router-link>
            </td>
            <td>{{ warranty.warrantyDesc }}</td>
            <td>{{ warranty.warrantyMonths }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WarrantyView', params: { warrantyId: warranty.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WarrantyEdit', params: { warrantyId: warranty.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(warranty)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="appApp.warranty.delete.question" data-cy="warrantyDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-warranty-heading" v-text="$t('appApp.warranty.delete.question', { id: removeId })">
          Are you sure you want to delete this Warranty?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-warranty"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeWarranty()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./warranty.component.ts"></script>
