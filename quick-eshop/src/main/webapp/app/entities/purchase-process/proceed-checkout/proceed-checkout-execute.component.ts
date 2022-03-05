import { Component, Vue, Inject } from 'vue-property-decorator';

import ProceedCheckoutService from './proceed-checkout.service';
import { ProceedCheckoutContext } from './proceed-checkout.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {
        confirmation: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ProceedCheckoutExecuteComponent extends Vue {
  private proceedCheckoutService: ProceedCheckoutService = new ProceedCheckoutService();
  private taskContext: ProceedCheckoutContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.proceedCheckoutService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.proceedCheckoutService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
