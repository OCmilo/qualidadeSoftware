import { Component, Vue, Inject } from 'vue-property-decorator';

import ProductService from '@/entities/product/product.service';
import { IProduct } from '@/shared/model/product.model';

import ProductFormService from './product-form.service';
import { ProductFormContext } from './product-form.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      product: {
        quantidade: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ProductFormExecuteComponent extends Vue {
  private productFormService: ProductFormService = new ProductFormService();
  private taskContext: ProductFormContext = {};

  @Inject('ProductService') private ProductService: () => ProductService;

  public Products: IProduct[] = [];
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
    this.productFormService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.productFormService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.ProductService()
      .retrieve()
      .then(res => {
        this.Products = res.data;
      });
  }
}
