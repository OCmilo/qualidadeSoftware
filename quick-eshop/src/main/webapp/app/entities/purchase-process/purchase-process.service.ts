import axios from 'axios';

import { IPurchaseProcess } from '@/shared/model/purchase-process.model';

const baseApiUrl = 'api/purchase-processes';

export default class PurchaseProcessService {
  public find(id: number): Promise<IPurchaseProcess> {
    return new Promise<IPurchaseProcess>((resolve, reject) => {
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

  public create(entity: IPurchaseProcess): Promise<IPurchaseProcess> {
    return new Promise<IPurchaseProcess>((resolve, reject) => {
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
