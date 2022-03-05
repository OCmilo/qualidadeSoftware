import { Component, Vue, Inject } from 'vue-property-decorator';

import FreightService from '@/entities/freight/freight.service';
import { IFreight } from '@/shared/model/freight.model';

import ChooseFreightService from './choose-freight.service';
import { ChooseFreightContext } from './choose-freight.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      purchase: {
        userAddress: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ChooseFreightExecuteComponent extends Vue {
  private chooseFreightService: ChooseFreightService = new ChooseFreightService();
  private taskContext: ChooseFreightContext = {};

  @Inject('freightService') private freightService: () => FreightService;

  public freights: IFreight[] = [];
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
    this.chooseFreightService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.chooseFreightService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.freightService()
      .retrieve()
      .then(res => {
        this.freights = res.data;
      });
  }
}
