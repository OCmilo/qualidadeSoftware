import { ITravelPlan } from '@/shared/model/travel-plan.model';

export interface ITravelPlanProcess {
  id?: number;
  processInstance?: any | null;
  travelPlan?: ITravelPlan | null;
}

export class TravelPlanProcess implements ITravelPlanProcess {
  constructor(public id?: number, public processInstance?: any | null, public travelPlan?: ITravelPlan | null) {}
}
