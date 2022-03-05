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
              <label class="form-control-label" v-text="$t('appApp.addWarranty.warranty')" for="add-warranty-warranty">Warranty</label>
              <select
                class="form-control"
                id="add-warranty-warranty"
                data-cy="warranty"
                name="warranty"
                v-model="taskContext.purchaseProcess.purchase.warranty"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.purchase.warranty && warrantyOption.id === taskContext.purchaseProcess.purchase.warranty.id
                      ? taskContext.purchaseProcess.purchase.warranty
                      : warrantyOption
                  "
                  v-for="warrantyOption in warranties"
                  :key="warrantyOption.id"
                >
                  {{ warrantyOption.warrantyDesc }}
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

<script lang="ts" src="./add-warranty-execute.component.ts"></script>
