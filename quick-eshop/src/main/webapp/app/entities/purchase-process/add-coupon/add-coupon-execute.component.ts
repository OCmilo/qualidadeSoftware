import { Component, Vue, Inject } from 'vue-property-decorator';

import CouponService from '@/entities/coupon/coupon.service';
import { ICoupon } from '@/shared/model/coupon.model';

import AddCouponService from './add-coupon.service';
import { AddCouponContext } from './add-coupon.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {},
    },
  },
};

@Component({
  validations,
})
export default class AddCouponExecuteComponent extends Vue {
  private addCouponService: AddCouponService = new AddCouponService();
  private taskContext: AddCouponContext = {};

  @Inject('couponService') private couponService: () => CouponService;

  public coupons: ICoupon[] = [];
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
    this.addCouponService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.addCouponService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.couponService()
      .retrieve()
      .then(res => {
        this.coupons = res.data;
      });
  }
}
