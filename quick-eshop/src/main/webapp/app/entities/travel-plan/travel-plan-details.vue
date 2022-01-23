<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="travelPlan">
        <h2 class="jh-entity-heading" data-cy="travelPlanDetailsHeading">
          <span v-text="$t('quickeshopApp.travelPlan.detail.title')">TravelPlan</span> {{ travelPlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.name')">Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.startDate')">Start Date</span>
          </dt>
          <dd>
            <span>{{ travelPlan.startDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.endDate')">End Date</span>
          </dt>
          <dd>
            <span>{{ travelPlan.endDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.airlineCompanyName')">Airline Company Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.airlineCompanyName }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.airlineTicketNumber')">Airline Ticket Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.airlineTicketNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.hotelName')">Hotel Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.hotelName }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.hotelBookingNumber')">Hotel Booking Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.hotelBookingNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.carCompanyName')">Car Company Name</span>
          </dt>
          <dd>
            <span>{{ travelPlan.carCompanyName }}</span>
          </dd>
          <dt>
            <span v-text="$t('quickeshopApp.travelPlan.carBookingNumber')">Car Booking Number</span>
          </dt>
          <dd>
            <span>{{ travelPlan.carBookingNumber }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./travel-plan-details.component.ts"></script>
