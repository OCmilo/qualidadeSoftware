import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IWarrantyForm } from '@/shared/model/warranty-form.model';

import WarrantyFormService from './warranty-form.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class WarrantyForm extends Vue {
  @Inject('warrantyFormService') private warrantyFormService: () => WarrantyFormService;
  private removeId: number = null;

  public warrantyForms: IWarrantyForm[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllWarrantyForms();
  }

  public clear(): void {
    this.retrieveAllWarrantyForms();
  }

  public retrieveAllWarrantyForms(): void {
    this.isFetching = true;

    this.warrantyFormService()
      .retrieve()
      .then(
        res => {
          this.warrantyForms = res.data;
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

  public prepareRemove(instance: IWarrantyForm): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeWarrantyForm(): void {
    this.warrantyFormService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.warrantyForm.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllWarrantyForms();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
