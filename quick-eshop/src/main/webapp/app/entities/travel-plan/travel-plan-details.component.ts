import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelPlan } from '@/shared/model/travel-plan.model';
import TravelPlanService from './travel-plan.service';

@Component
export default class TravelPlanDetails extends Vue {
  @Inject('travelPlanService') private travelPlanService: () => TravelPlanService;
  public travelPlan: ITravelPlan = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.travelPlanId) {
        vm.retrieveTravelPlan(to.params.travelPlanId);
      }
    });
  }

  public retrieveTravelPlan(travelPlanId) {
    this.travelPlanService()
      .find(travelPlanId)
      .then(res => {
        this.travelPlan = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
