import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IWarranty } from '@/shared/model/warranty.model';

import WarrantyService from './warranty.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Warranty extends Vue {
  @Inject('warrantyService') private warrantyService: () => WarrantyService;
  private removeId: number = null;

  public warranties: IWarranty[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllWarrantys();
  }

  public clear(): void {
    this.retrieveAllWarrantys();
  }

  public retrieveAllWarrantys(): void {
    this.isFetching = true;

    this.warrantyService()
      .retrieve()
      .then(
        res => {
          this.warranties = res.data;
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

  public prepareRemove(instance: IWarranty): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeWarranty(): void {
    this.warrantyService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.warranty.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllWarrantys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
