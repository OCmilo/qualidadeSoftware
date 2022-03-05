<template>
  <div>
    <h2 id="page-heading" data-cy="CoupomFormHeading">
      <span v-text="$t('quickeshopApp.coupomForm.home.title')" id="coupom-form-heading">Coupom Forms</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('quickeshopApp.coupomForm.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'CoupomFormCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-coupom-form"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('quickeshopApp.coupomForm.home.createLabel')"> Create a new Coupom Form </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && coupomForms && coupomForms.length === 0">
      <span v-text="$t('quickeshopApp.coupomForm.home.notFound')">No coupomForms found</span>
    </div>
    <div class="table-responsive" v-if="coupomForms && coupomForms.length > 0">
      <table class="table table-striped" aria-describedby="coupomForms">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.coupomForm.nome')">Nome</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.coupomForm.desconto')">Desconto</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="coupomForm in coupomForms" :key="coupomForm.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CoupomFormView', params: { coupomFormId: coupomForm.id } }">{{ coupomForm.id }}</router-link>
            </td>
            <td>{{ coupomForm.nome }}</td>
            <td>{{ coupomForm.desconto }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CoupomFormView', params: { coupomFormId: coupomForm.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CoupomFormEdit', params: { coupomFormId: coupomForm.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(coupomForm)"
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
        ><span id="quickeshopApp.coupomForm.delete.question" data-cy="coupomFormDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-coupomForm-heading" v-text="$t('quickeshopApp.coupomForm.delete.question', { id: removeId })">
          Are you sure you want to delete this Coupom Form?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-coupomForm"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCoupomForm()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./coupom-form.component.ts"></script>
