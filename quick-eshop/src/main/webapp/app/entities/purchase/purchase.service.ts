import axios from 'axios';

import { IPurchase } from '@/shared/model/purchase.model';

const baseApiUrl = 'api/purchases';

export default class PurchaseService {
  public find(id: number): Promise<IPurchase> {
    return new Promise<IPurchase>((resolve, reject) => {
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
