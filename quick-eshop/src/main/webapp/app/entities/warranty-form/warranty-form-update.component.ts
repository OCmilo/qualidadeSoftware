import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWarrantyForm, WarrantyForm } from '@/shared/model/warranty-form.model';
import WarrantyFormService from './warranty-form.service';

const validations: any = {
  warrantyForm: {
    name: {},
    quantity: {},
  },
};

@Component({
  validations,
})
export default class WarrantyFormUpdate extends Vue {
  @Inject('warrantyFormService') private warrantyFormService: () => WarrantyFormService;
  public warrantyForm: IWarrantyForm = new WarrantyForm();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.warrantyFormId) {
        vm.retrieveWarrantyForm(to.params.warrantyFormId);
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
    if (this.warrantyForm.id) {
      this.warrantyFormService()
        .update(this.warrantyForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.warrantyForm.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.warrantyFormService()
        .create(this.warrantyForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.warrantyForm.created', { param: param.id });
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

  public retrieveWarrantyForm(warrantyFormId): void {
    this.warrantyFormService()
      .find(warrantyFormId)
      .then(res => {
        this.warrantyForm = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
