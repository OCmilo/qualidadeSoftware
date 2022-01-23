import axios from 'axios';

import { ITravelPlan } from '@/shared/model/travel-plan.model';

const baseApiUrl = 'api/travel-plans';

export default class TravelPlanService {
  public find(id: number): Promise<ITravelPlan> {
    return new Promise<ITravelPlan>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
