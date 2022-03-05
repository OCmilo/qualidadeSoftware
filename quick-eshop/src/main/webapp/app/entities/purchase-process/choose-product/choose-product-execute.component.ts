import { Component, Vue, Inject } from 'vue-property-decorator';

import ProductService from '@/entities/product/product.service';
import { IProduct } from '@/shared/model/product.model';

import ChooseProductService from './choose-product.service';
import { ChooseProductContext } from './choose-product.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {
        quantity: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ChooseProductExecuteComponent extends Vue {
  private chooseProductService: ChooseProductService = new ChooseProductService();
  private taskContext: ChooseProductContext = {};

  @Inject('productService') private productService: () => ProductService;

  public products: IProduct[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.chooseProductService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.chooseProductService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.productService()
      .retrieve()
      .then(res => {
        this.products = res.data;
      });
  }
}
