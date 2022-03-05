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
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.quantity')" for="choose-product-quantity">Quantity</label>
              <input
                type="number"
                class="form-control"
                name="quantity"
                id="choose-product-quantity"
                data-cy="quantity"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.purchase.quantity.$invalid,
                  invalid: $v.taskContext.purchaseProcess.purchase.quantity.$invalid,
                }"
                v-model.number="$v.taskContext.purchaseProcess.purchase.quantity.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.withWarranty')" for="choose-product-withWarranty"
                >With Warranty</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="withWarranty"
                id="choose-product-withWarranty"
                data-cy="withWarranty"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.purchase.withWarranty.$invalid,
                  invalid: $v.taskContext.purchaseProcess.purchase.withWarranty.$invalid,
                }"
                v-model="$v.taskContext.purchaseProcess.purchase.withWarranty.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('appApp.chooseProduct.product')" for="choose-product-product">Product</label>
              <select
                class="form-control"
                id="choose-product-product"
                data-cy="product"
                name="product"
                v-model="taskContext.purchaseProcess.purchase.product"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.purchase.product && productOption.id === taskContext.purchaseProcess.purchase.product.id
                      ? taskContext.purchaseProcess.purchase.product
                      : productOption
                  "
                  v-for="productOption in products"
                  :key="productOption.id"
                >
                  {{ productOption.productName }}
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

<script lang="ts" src="./choose-product-execute.component.ts"></script>
