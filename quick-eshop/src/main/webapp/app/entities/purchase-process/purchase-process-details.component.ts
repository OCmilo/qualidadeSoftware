import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPurchaseProcess } from '@/shared/model/purchase-process.model';
import PurchaseProcessService from './purchase-process.service';

@Component
export default class PurchaseProcessDetailsComponent extends Vue {
  @Inject('purchaseProcessService') private purchaseProcessService: () => PurchaseProcessService;
  public purchaseProcess: IPurchaseProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrievePurchaseProcess(to.params.processInstanceId);
      }
    });
  }

  public retrievePurchaseProcess(purchaseProcessId) {
    this.isFetching = true;
    this.purchaseProcessService()
      .find(purchaseProcessId)
      .then(
        res => {
          this.purchaseProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
