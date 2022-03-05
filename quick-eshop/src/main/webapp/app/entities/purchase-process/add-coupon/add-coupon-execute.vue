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
              <label class="form-control-label" v-text="$t('appApp.addCoupon.coupon')" for="add-coupon-coupon">Coupon</label>
              <select
                class="form-control"
                id="add-coupon-coupon"
                data-cy="coupon"
                name="coupon"
                v-model="taskContext.purchaseProcess.purchase.coupon"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.purchase.coupon && couponOption.id === taskContext.purchaseProcess.purchase.coupon.id
                      ? taskContext.purchaseProcess.purchase.coupon
                      : couponOption
                  "
                  v-for="couponOption in coupons"
                  :key="couponOption.id"
                >
                  {{ couponOption.couponName }}
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

<script lang="ts" src="./add-coupon-execute.component.ts"></script>
