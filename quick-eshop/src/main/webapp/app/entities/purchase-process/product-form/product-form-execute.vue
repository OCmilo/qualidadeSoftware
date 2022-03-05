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
              <label class="form-control-label" v-text="$t('quickeshopApp.productForm.quantidade')" for="product-form-quantidade"
                >Quantidade</label
              >
              <input
                type="number"
                class="form-control"
                name="quantidade"
                id="product-form-quantidade"
                data-cy="quantidade"
                :class="{
                  valid: !$v.taskContext.purchaseProcess.product.quantidade.$invalid,
                  invalid: $v.taskContext.purchaseProcess.product.quantidade.$invalid,
                }"
                v-model.number="$v.taskContext.purchaseProcess.product.quantidade.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('quickeshopApp.productForm.produto')" for="product-form-produto">Produto</label>
              <select
                class="form-control"
                id="product-form-produto"
                data-cy="produto"
                name="produto"
                v-model="taskContext.purchaseProcess.product.produto"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.purchaseProcess.product.produto && ProductOption.id === taskContext.purchaseProcess.product.produto.id
                      ? taskContext.purchaseProcess.product.produto
                      : ProductOption
                  "
                  v-for="ProductOption in Products"
                  :key="ProductOption.id"
                >
                  {{ ProductOption.name }}
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

<script lang="ts" src="./product-form-execute.component.ts"></script>
