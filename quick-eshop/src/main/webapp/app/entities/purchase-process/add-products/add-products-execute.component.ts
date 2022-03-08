import { Component, Vue, Inject } from 'vue-property-decorator';

import AddProductsService from './add-products.service';
import { AddProductsContext } from './add-products.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {
        addProducts: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class AddProductsExecuteComponent extends Vue {
  private addProductsService: AddProductsService = new AddProductsService();
  private taskContext: AddProductsContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.addProductsService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.addProductsService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
