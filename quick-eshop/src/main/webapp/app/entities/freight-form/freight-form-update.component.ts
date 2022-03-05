import { Component, Vue, Inject } from 'vue-property-decorator';

import { IFreightForm, FreightForm } from '@/shared/model/freight-form.model';
import FreightFormService from './freight-form.service';

const validations: any = {
  freightForm: {
    name: {},
    quantity: {},
  },
};

@Component({
  validations,
})
export default class FreightFormUpdate extends Vue {
  @Inject('freightFormService') private freightFormService: () => FreightFormService;
  public freightForm: IFreightForm = new FreightForm();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.freightFormId) {
        vm.retrieveFreightForm(to.params.freightFormId);
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
    if (this.freightForm.id) {
      this.freightFormService()
        .update(this.freightForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.freightForm.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.freightFormService()
        .create(this.freightForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.freightForm.created', { param: param.id });
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

  public retrieveFreightForm(freightFormId): void {
    this.freightFormService()
      .find(freightFormId)
      .then(res => {
        this.freightForm = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
