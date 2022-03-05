import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWarrantyForm } from '@/shared/model/warranty-form.model';
import WarrantyFormService from './warranty-form.service';

@Component
export default class WarrantyFormDetails extends Vue {
  @Inject('warrantyFormService') private warrantyFormService: () => WarrantyFormService;
  public warrantyForm: IWarrantyForm = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.warrantyFormId) {
        vm.retrieveWarrantyForm(to.params.warrantyFormId);
      }
    });
  }

  public retrieveWarrantyForm(warrantyFormId) {
    this.warrantyFormService()
      .find(warrantyFormId)
      .then(res => {
        this.warrantyForm = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
