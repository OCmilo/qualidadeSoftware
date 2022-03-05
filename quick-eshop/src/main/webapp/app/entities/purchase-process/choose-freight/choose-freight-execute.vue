<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('appApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.address')" for="choose-freight-address">Address</label>
              <input
                type="text"
                class="form-control"
                name="address"
                id="choose-freight-address"
                data-cy="address"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.purchase.address.$invalid,
                  invalid: $v.taskContext.purchaseProcess.purchase.address.$invalid,
                }"
                v-model="$v.taskContext.purchaseProcess.purchase.address.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.withCoupon')" for="choose-freight-withCoupon"
                >With Coupon</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="withCoupon"
                id="choose-freight-withCoupon"
                data-cy="withCoupon"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.purchase.withCoupon.$invalid,
                  invalid: $v.taskContext.purchaseProcess.purchase.withCoupon.$invalid,
                }"
                v-model="$v.taskContext.purchaseProcess.purchase.withCoupon.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.freight')" for="choose-freight-freight">Freight</label>
              <select
                class="form-control"
                id="choose-freight-freight"
                data-cy="freight"
                name="freight"
                v-model="taskContext.purchaseProcess.purchase.freight"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.purchase.freight && freightOption.id === taskContext.purchaseProcess.purchase.freight.id
                      ? taskContext.purchaseProcess.purchase.freight
                      : freightOption
                  "
                  v-for="freightOption in freights"
                  :key="freightOption.id"
                >
                  {{ freightOption.freighter }}
                </option>
              </select>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./choose-freight-execute.component.ts"></script>
