import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPurchase } from '@/shared/model/purchase.model';
import PurchaseService from './purchase.service';

@Component
export default class PurchaseDetails extends Vue {
  @Inject('purchaseService') private purchaseService: () => PurchaseService;
  public purchase: IPurchase = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.purchaseId) {
        vm.retrievePurchase(to.params.purchaseId);
      }
    });
  }

  public retrievePurchase(purchaseId) {
    this.purchaseService()
      .find(purchaseId)
      .then(res => {
        this.purchase = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
