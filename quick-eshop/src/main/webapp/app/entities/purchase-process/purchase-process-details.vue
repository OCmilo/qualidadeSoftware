<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="purchaseProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="purchaseProcessDetailsHeading">
          <span v-text="$t('quickeshopApp.purchaseProcess.detail.title')">PurchaseProcess</span> {{ purchaseProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="purchaseProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('quickeshopApp.purchaseProcess.detail.title')">PurchaseProcess</span> #{{ purchaseProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="purchaseProcess.product">
              <h5 class="card-header">
                <span class="title"> Product #{{ purchaseProcess.product.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseProcess.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="name"
                    id="product-name"
                    data-cy="name"
                    v-model="purchaseProcess.product.name"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseProcess.barcode')">barcode</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="barcode"
                    id="product-barcode"
                    data-cy="barcode"
                    v-model="purchaseProcess.product.barcode"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseProcess.price')">price</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="price"
                    id="product-price"
                    data-cy="price"
                    v-model="purchaseProcess.product.price"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('quickeshopApp.purchaseProcess.availableQuantity')">availableQuantity</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="availableQuantity"
                    id="product-availableQuantity"
                    data-cy="availableQuantity"
                    v-model="purchaseProcess.product.availableQuantity"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./purchase-process-details.component.ts"></script>
