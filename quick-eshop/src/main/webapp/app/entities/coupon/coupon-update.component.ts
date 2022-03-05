import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICoupon, Coupon } from '@/shared/model/coupon.model';
import CouponService from './coupon.service';

const validations: any = {
  coupon: {
    couponName: {},
    couponDiscount: {},
  },
};

@Component({
  validations,
})
export default class CouponUpdate extends Vue {
  @Inject('couponService') private couponService: () => CouponService;
  public coupon: ICoupon = new Coupon();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.couponId) {
        vm.retrieveCoupon(to.params.couponId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.coupon.id) {
      this.couponService()
        .update(this.coupon)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('appApp.coupon.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.couponService()
        .create(this.coupon)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('appApp.coupon.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveCoupon(couponId): void {
    this.couponService()
      .find(couponId)
      .then(res => {
        this.coupon = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
