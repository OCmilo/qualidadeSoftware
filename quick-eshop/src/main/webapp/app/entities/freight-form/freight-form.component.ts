import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IFreightForm } from '@/shared/model/freight-form.model';

import FreightFormService from './freight-form.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class FreightForm extends Vue {
  @Inject('freightFormService') private freightFormService: () => FreightFormService;
  private removeId: number = null;

  public freightForms: IFreightForm[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllFreightForms();
  }

  public clear(): void {
    this.retrieveAllFreightForms();
  }

  public retrieveAllFreightForms(): void {
    this.isFetching = true;

    this.freightFormService()
      .retrieve()
      .then(
        res => {
          this.freightForms = res.data;
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

  public prepareRemove(instance: IFreightForm): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeFreightForm(): void {
    this.freightFormService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.freightForm.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllFreightForms();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
