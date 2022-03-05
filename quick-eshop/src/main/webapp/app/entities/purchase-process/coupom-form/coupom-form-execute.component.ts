import { Component, Vue, Inject } from 'vue-property-decorator';

import CoupomService from '@/entities/coupom/coupom.service';
import { ICoupom } from '@/shared/model/coupom.model';

import CoupomFormService from './coupom-form.service';
import { CoupomFormContext } from './coupom-form.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      coupom: {},
    },
  },
};

@Component({
  validations,
})
export default class CoupomFormExecuteComponent extends Vue {
  private coupomFormService: CoupomFormService = new CoupomFormService();
  private taskContext: CoupomFormContext = {};

  @Inject('CoupomService') private CoupomService: () => CoupomService;

  public Coupoms: ICoupom[] = [];
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
    this.coupomFormService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.coupomFormService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.CoupomService()
      .retrieve()
      .then(res => {
        this.Coupoms = res.data;
      });
  }
}
