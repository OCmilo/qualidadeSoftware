import { Component, Vue, Inject } from 'vue-property-decorator';

import WarrantyService from '@/entities/warranty/warranty.service';
import { IWarranty } from '@/shared/model/warranty.model';

import WarrantyFormService from './warranty-form.service';
import { WarrantyFormContext } from './warranty-form.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      warranty: {},
    },
  },
};

@Component({
  validations,
})
export default class WarrantyFormExecuteComponent extends Vue {
  private warrantyFormService: WarrantyFormService = new WarrantyFormService();
  private taskContext: WarrantyFormContext = {};

  @Inject('WarrantyService') private WarrantyService: () => WarrantyService;

  public Warranties: IWarranty[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.warrantyFormService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.warrantyFormService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.WarrantyService()
      .retrieve()
      .then(res => {
        this.Warranties = res.data;
      });
  }
}
