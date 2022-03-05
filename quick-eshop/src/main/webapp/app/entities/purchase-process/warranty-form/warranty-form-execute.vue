<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('quickeshopApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('quickeshopApp.warrantyForm.garantia')" for="warranty-form-garantia"
                >Garantia</label
              >
              <select
                class="form-control"
                id="warranty-form-garantia"
                data-cy="garantia"
                name="garantia"
                v-model="taskContext.purchaseProcess.warranty.garantia"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.warranty.garantia && WarrantyOption.id === taskContext.purchaseProcess.warranty.garantia.id
                      ? taskContext.purchaseProcess.warranty.garantia
                      : WarrantyOption
                  "
                  v-for="WarrantyOption in Warranties"
                  :key="WarrantyOption.id"
                >
                  {{ WarrantyOption.tempo }}
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

<script lang="ts" src="./warranty-form-execute.component.ts"></script>
