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
              <label class="form-control-label" v-text="$t('appApp.chooseFreight.userAddress')" for="choose-freight-userAddress"
                >User Address</label
              >
              <input
                type="text"
                class="form-control"
                name="userAddress"
                id="choose-freight-userAddress"
                data-cy="userAddress"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.purchase.userAddress.$invalid,
                  invalid: $v.taskContext.purchaseProcess.purchase.userAddress.$invalid,
                }"
                v-model="$v.taskContext.purchaseProcess.purchase.userAddress.$model"
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
