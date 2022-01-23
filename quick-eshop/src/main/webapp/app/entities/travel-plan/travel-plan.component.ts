import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITravelPlan } from '@/shared/model/travel-plan.model';

import TravelPlanService from './travel-plan.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TravelPlan extends Vue {
  @Inject('travelPlanService') private travelPlanService: () => TravelPlanService;
  private removeId: number = null;

  public travelPlans: ITravelPlan[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTravelPlans();
  }

  public clear(): void {
    this.retrieveAllTravelPlans();
  }

  public retrieveAllTravelPlans(): void {
    this.isFetching = true;

    this.travelPlanService()
      .retrieve()
      .then(
        res => {
          this.travelPlans = res.data;
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
