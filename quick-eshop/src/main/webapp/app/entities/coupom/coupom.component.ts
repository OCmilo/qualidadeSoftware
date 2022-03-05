import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICoupom } from '@/shared/model/coupom.model';

import CoupomService from './coupom.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Coupom extends Vue {
  @Inject('coupomService') private coupomService: () => CoupomService;
  private removeId: number = null;

  public coupoms: ICoupom[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCoupoms();
  }

  public clear(): void {
    this.retrieveAllCoupoms();
  }

  public retrieveAllCoupoms(): void {
    this.isFetching = true;

    this.coupomService()
      .retrieve()
      .then(
        res => {
          this.coupoms = res.data;
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

  public prepareRemove(instance: ICoupom): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCoupom(): void {
    this.coupomService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('quickeshopApp.coupom.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCoupoms();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
