import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPurchase } from '@/shared/model/purchase.model';

import PurchaseService from './purchase.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Purchase extends Vue {
  @Inject('purchaseService') private purchaseService: () => PurchaseService;
  private removeId: number = null;

  public purchases: IPurchase[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPurchases();
  }

  public clear(): void {
    this.retrieveAllPurchases();
  }

  public retrieveAllPurchases(): void {
    this.isFetching = true;

    this.purchaseService()
      .retrieve()
      .then(
        res => {
          this.purchases = res.data;
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
