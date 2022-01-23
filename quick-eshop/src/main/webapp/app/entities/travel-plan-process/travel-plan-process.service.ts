import axios from 'axios';

import { ITravelPlanProcess } from '@/shared/model/travel-plan-process.model';

const baseApiUrl = 'api/travel-plan-processes';

export default class TravelPlanProcessService {
  public find(id: number): Promise<ITravelPlanProcess> {
    return new Promise<ITravelPlanProcess>((resolve, reject) => {
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

  public create(entity: ITravelPlanProcess): Promise<ITravelPlanProcess> {
    return new Promise<ITravelPlanProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
