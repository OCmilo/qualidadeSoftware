<template>
  <div>
    <h2 id="page-heading" data-cy="CoupomHeading">
      <span v-text="$t('quickeshopApp.coupom.home.title')" id="coupom-heading">Coupoms</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('quickeshopApp.coupom.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'CoupomCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-coupom"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('quickeshopApp.coupom.home.createLabel')"> Create a new Coupom </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && coupoms && coupoms.length === 0">
      <span v-text="$t('quickeshopApp.coupom.home.notFound')">No coupoms found</span>
    </div>
    <div class="table-responsive" v-if="coupoms && coupoms.length > 0">
      <table class="table table-striped" aria-describedby="coupoms">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.coupom.nome')">Nome</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.coupom.desconto')">Desconto</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="coupom in coupoms" :key="coupom.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CoupomView', params: { coupomId: coupom.id } }">{{ coupom.id }}</router-link>
            </td>
            <td>{{ coupom.nome }}</td>
            <td>{{ coupom.desconto }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CoupomView', params: { coupomId: coupom.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CoupomEdit', params: { coupomId: coupom.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(coupom)"
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
        ><span id="quickeshopApp.coupom.delete.question" data-cy="coupomDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-coupom-heading" v-text="$t('quickeshopApp.coupom.delete.question', { id: removeId })">
          Are you sure you want to delete this Coupom?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-coupom"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCoupom()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./coupom.component.ts"></script>
