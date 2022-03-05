<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('appApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.quantity')">quantity</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="quantity"
                id="purchase-quantity"
                data-cy="quantity"
                v-model="taskContext.purchaseProcess.purchase.quantity"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.withWarranty')">withWarranty</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="withWarranty"
                id="purchase-withWarranty"
                data-cy="withWarranty"
                v-model="taskContext.purchaseProcess.purchase.withWarranty"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.product')" for="choose-product-product">Product</label>
              <input
                v-if="taskContext.purchaseProcess.purchase.product"
                readonly
                type="text"
                class="form-control"
                name="product"
                id="purchase-product"
                data-cy="product"
                :value="taskContext.purchaseProcess.purchase.product.productName"
              />
              <input v-else readonly type="text" class="form-control" name="product" id="purchase-product" data-cy="product" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/PurchaseProcess/task/ChooseProduct/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./choose-product-details.component.ts"></script>
