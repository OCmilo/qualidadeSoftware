<template>
  <div>
    <h2 id="page-heading" data-cy="TravelPlanHeading">
      <span v-text="$t('quickeshopApp.travelPlan.home.title')" id="travel-plan-heading">Travel Plans</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('quickeshopApp.travelPlan.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && travelPlans && travelPlans.length === 0">
      <span v-text="$t('quickeshopApp.travelPlan.home.notFound')">No travelPlans found</span>
    </div>
    <div class="table-responsive" v-if="travelPlans && travelPlans.length > 0">
      <table class="table table-striped" aria-describedby="travelPlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.name')">Name</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.endDate')">End Date</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.airlineCompanyName')">Airline Company Name</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.airlineTicketNumber')">Airline Ticket Number</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.hotelName')">Hotel Name</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.hotelBookingNumber')">Hotel Booking Number</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.carCompanyName')">Car Company Name</span></th>
            <th scope="row"><span v-text="$t('quickeshopApp.travelPlan.carBookingNumber')">Car Booking Number</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="travelPlan in travelPlans" :key="travelPlan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TravelPlanView', params: { travelPlanId: travelPlan.id } }">{{ travelPlan.id }}</router-link>
            </td>
            <td>{{ travelPlan.name }}</td>
            <td>{{ travelPlan.startDate }}</td>
            <td>{{ travelPlan.endDate }}</td>
            <td>{{ travelPlan.airlineCompanyName }}</td>
            <td>{{ travelPlan.airlineTicketNumber }}</td>
            <td>{{ travelPlan.hotelName }}</td>
            <td>{{ travelPlan.hotelBookingNumber }}</td>
            <td>{{ travelPlan.carCompanyName }}</td>
            <td>{{ travelPlan.carBookingNumber }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TravelPlanView', params: { travelPlanId: travelPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="quickeshopApp.travelPlan.delete.question" data-cy="travelPlanDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-travelPlan-heading" v-text="$t('quickeshopApp.travelPlan.delete.question', { id: removeId })">
          Are you sure you want to delete this Travel Plan?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-travelPlan"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTravelPlan()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./travel-plan.component.ts"></script>
