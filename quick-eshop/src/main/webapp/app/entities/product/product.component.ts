import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProduct } from '@/shared/model/product.model';

import ProductService from './product.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Product extends Vue {
  @Inject('productService') private productService: () => ProductService;
  private removeId: number = null;

  public products: IProduct[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProducts();
  }

  public clear(): void {
    this.retrieveAllProducts();
  }

  public retrieveAllProducts(): void {
    this.isFetching = true;

    this.productService()
      .retrieve()
      .then(
        res => {
          this.products = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
