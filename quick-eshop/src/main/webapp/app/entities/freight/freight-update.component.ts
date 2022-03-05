import { Component, Vue, Inject } from 'vue-property-decorator';

import { IFreight, Freight } from '@/shared/model/freight.model';
import FreightService from './freight.service';

const validations: any = {
  freight: {
    empresa: {},
    valor: {},
  },
};

@Component({
  validations,
})
export default class FreightUpdate extends Vue {
  @Inject('freightService') private freightService: () => FreightService;
  public freight: IFreight = new Freight();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.freightId) {
        vm.retrieveFreight(to.params.freightId);
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
    if (this.freight.id) {
      this.freightService()
        .update(this.freight)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.freight.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.freightService()
        .create(this.freight)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('quickeshopApp.freight.created', { param: param.id });
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

  public retrieveFreight(freightId): void {
    this.freightService()
      .find(freightId)
      .then(res => {
        this.freight = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
