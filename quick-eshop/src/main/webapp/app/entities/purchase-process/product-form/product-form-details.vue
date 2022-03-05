<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('quickeshopApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('quickeshopApp.productForm.quantidade')">quantidade</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="quantidade"
                id="product-quantidade"
                data-cy="quantidade"
                v-model="taskContext.purchaseProcess.product.quantidade"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('quickeshopApp.productForm.produto')" for="product-form-produto">Produto</label>
              <input
                v-if="taskContext.purchaseProcess.product.produto"
                readonly
                type="text"
                class="form-control"
                name="produto"
                id="product-produto"
                data-cy="produto"
                :value="taskContext.purchaseProcess.product.produto.name"
              />
              <input v-else readonly type="text" class="form-control" name="produto" id="product-produto" data-cy="produto" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/PurchaseProcess/task/search_product/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./product-form-details.component.ts"></script>
