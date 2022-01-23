import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProduct } from '@/shared/model/product.model';
import ProductService from './product.service';

@Component
export default class ProductDetails extends Vue {
  @Inject('productService') private productService: () => ProductService;
  public product: IProduct = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productId) {
        vm.retrieveProduct(to.params.productId);
      }
    });
  }

  public retrieveProduct(productId) {
    this.productService()
      .find(productId)
      .then(res => {
        this.product = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
