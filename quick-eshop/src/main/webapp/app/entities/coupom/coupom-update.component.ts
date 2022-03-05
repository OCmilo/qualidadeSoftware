import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICoupom, Coupom } from '@/shared/model/coupom.model';
import CoupomService from './coupom.service';

const validations: any = {
  coupom: {
    nome: {},
    desconto: {},
  },
};

@Component({
  validations,
})
export default class CoupomUpdate extends Vue {
  @Inject('coupomService') private coupomService: () => CoupomService;
  public coupom: ICoupom = new Coupom();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.coupomId) {
        vm.retrieveCoupom(to.params.coupomId);
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
    if (this.coupom.id) {
      this.coupomService()
        .update(this.coupom)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.coupom.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.coupomService()
        .create(this.coupom)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.coupom.created', { param: param.id });
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

  public retrieveCoupom(coupomId): void {
    this.coupomService()
      .find(coupomId)
      .then(res => {
        this.coupom = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
