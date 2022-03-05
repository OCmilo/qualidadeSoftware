import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWarranty, Warranty } from '@/shared/model/warranty.model';
import WarrantyService from './warranty.service';

const validations: any = {
  warranty: {
    warrantyDesc: {},
    warrantyMonths: {},
  },
};

@Component({
  validations,
})
export default class WarrantyUpdate extends Vue {
  @Inject('warrantyService') private warrantyService: () => WarrantyService;
  public warranty: IWarranty = new Warranty();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.warrantyId) {
        vm.retrieveWarranty(to.params.warrantyId);
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
    if (this.warranty.id) {
      this.warrantyService()
        .update(this.warranty)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('appApp.warranty.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.warrantyService()
        .create(this.warranty)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('appApp.warranty.created', { param: param.id });
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

  public retrieveWarranty(warrantyId): void {
    this.warrantyService()
      .find(warrantyId)
      .then(res => {
        this.warranty = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
