import { Component, Vue, Inject } from 'vue-property-decorator';

import AddCouponService from './add-coupon.service';
import { AddCouponContext } from './add-coupon.model';

@Component
export default class AddCouponDetailsComponent extends Vue {
  private addCouponService: AddCouponService = new AddCouponService();
  private taskContext: AddCouponContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.addCouponService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
