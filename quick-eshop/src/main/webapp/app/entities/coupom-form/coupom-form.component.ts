import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICoupomForm } from '@/shared/model/coupom-form.model';

import CoupomFormService from './coupom-form.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class CoupomForm extends Vue {
  @Inject('coupomFormService') private coupomFormService: () => CoupomFormService;
  private removeId: number = null;

  public coupomForms: ICoupomForm[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCoupomForms();
  }

  public clear(): void {
    this.retrieveAllCoupomForms();
  }

  public retrieveAllCoupomForms(): void {
    this.isFetching = true;

    this.coupomFormService()
      .retrieve()
      .then(
        res => {
          this.coupomForms = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ICoupomForm): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCoupomForm(): void {
    this.coupomFormService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.coupomForm.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCoupomForms();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
