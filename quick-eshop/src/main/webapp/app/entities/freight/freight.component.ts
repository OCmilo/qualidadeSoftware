import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IFreight } from '@/shared/model/freight.model';

import FreightService from './freight.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Freight extends Vue {
  @Inject('freightService') private freightService: () => FreightService;
  private removeId: number = null;

  public freights: IFreight[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllFreights();
  }

  public clear(): void {
    this.retrieveAllFreights();
  }

  public retrieveAllFreights(): void {
    this.isFetching = true;

    this.freightService()
      .retrieve()
      .then(
        res => {
          this.freights = res.data;
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

  public prepareRemove(instance: IFreight): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeFreight(): void {
    this.freightService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.freight.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllFreights();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
