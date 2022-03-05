import { Component, Vue, Inject } from 'vue-property-decorator';

import ProceedCheckoutService from './proceed-checkout.service';
import { ProceedCheckoutContext } from './proceed-checkout.model';

@Component
export default class ProceedCheckoutDetailsComponent extends Vue {
  private proceedCheckoutService: ProceedCheckoutService = new ProceedCheckoutService();
  private taskContext: ProceedCheckoutContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.proceedCheckoutService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
