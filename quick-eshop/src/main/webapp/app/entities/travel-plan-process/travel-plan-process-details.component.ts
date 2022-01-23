import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelPlanProcess } from '@/shared/model/travel-plan-process.model';
import TravelPlanProcessService from './travel-plan-process.service';

@Component
export default class TravelPlanProcessDetailsComponent extends Vue {
  @Inject('travelPlanProcessService') private travelPlanProcessService: () => TravelPlanProcessService;
  public travelPlanProcess: ITravelPlanProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveTravelPlanProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveTravelPlanProcess(travelPlanProcessId) {
    this.isFetching = true;
    this.travelPlanProcessService()
      .find(travelPlanProcessId)
      .then(
        res => {
          this.travelPlanProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
