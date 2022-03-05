import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICoupomForm, CoupomForm } from '@/shared/model/coupom-form.model';
import CoupomFormService from './coupom-form.service';

const validations: any = {
  coupomForm: {
    nome: {},
    desconto: {},
  },
};

@Component({
  validations,
})
export default class CoupomFormUpdate extends Vue {
  @Inject('coupomFormService') private coupomFormService: () => CoupomFormService;
  public coupomForm: ICoupomForm = new CoupomForm();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.coupomFormId) {
        vm.retrieveCoupomForm(to.params.coupomFormId);
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
    if (this.coupomForm.id) {
      this.coupomFormService()
        .update(this.coupomForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.coupomForm.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.coupomFormService()
        .create(this.coupomForm)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.coupomForm.created', { param: param.id });
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

  public retrieveCoupomForm(coupomFormId): void {
    this.coupomFormService()
      .find(coupomFormId)
      .then(res => {
        this.coupomForm = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
