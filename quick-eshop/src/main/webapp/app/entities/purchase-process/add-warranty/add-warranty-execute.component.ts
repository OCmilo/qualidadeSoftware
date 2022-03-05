import { Component, Vue, Inject } from 'vue-property-decorator';

import WarrantyService from '@/entities/warranty/warranty.service';
import { IWarranty } from '@/shared/model/warranty.model';

import AddWarrantyService from './add-warranty.service';
import { AddWarrantyContext } from './add-warranty.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {},
    },
  },
};

@Component({
  validations,
})
export default class AddWarrantyExecuteComponent extends Vue {
  private addWarrantyService: AddWarrantyService = new AddWarrantyService();
  private taskContext: AddWarrantyContext = {};

  @Inject('warrantyService') private warrantyService: () => WarrantyService;

  public warranties: IWarranty[] = [];
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
    this.addWarrantyService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.addWarrantyService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.warrantyService()
      .retrieve()
      .then(res => {
        this.warranties = res.data;
      });
  }
}
