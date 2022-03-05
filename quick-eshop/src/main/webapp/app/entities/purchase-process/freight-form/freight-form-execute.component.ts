import { Component, Vue, Inject } from 'vue-property-decorator';

import FreightService from '@/entities/freight/freight.service';
import { IFreight } from '@/shared/model/freight.model';

import FreightFormService from './freight-form.service';
import { FreightFormContext } from './freight-form.model';

const validations: any = {
  taskContext: {
    purchaseProcess: {
      freight: {},
    },
  },
};

@Component({
  validations,
})
export default class FreightFormExecuteComponent extends Vue {
  private freightFormService: FreightFormService = new FreightFormService();
  private taskContext: FreightFormContext = {};

  @Inject('FreightService') private FreightService: () => FreightService;

  public Freights: IFreight[] = [];
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
    this.freightFormService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.freightFormService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.FreightService()
      .retrieve()
      .then(res => {
        this.Freights = res.data;
      });
  }
}
