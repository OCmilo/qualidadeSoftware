import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWarranty } from '@/shared/model/warranty.model';
import WarrantyService from './warranty.service';

@Component
export default class WarrantyDetails extends Vue {
  @Inject('warrantyService') private warrantyService: () => WarrantyService;
  public warranty: IWarranty = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.warrantyId) {
        vm.retrieveWarranty(to.params.warrantyId);
      }
    });
  }

  public retrieveWarranty(warrantyId) {
    this.warrantyService()
      .find(warrantyId)
      .then(res => {
        this.warranty = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
