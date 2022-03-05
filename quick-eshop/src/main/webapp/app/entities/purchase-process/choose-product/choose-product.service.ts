import axios from 'axios';
import { ChooseProductContext } from './choose-product.model';

const baseApiUrl = 'api/purchase-process/choose-product';

export default class ChooseProductService {
  public loadContext(taskId: number): Promise<ChooseProductContext> {
    return new Promise<ChooseProductContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<ChooseProductContext> {
    return new Promise<ChooseProductContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(chooseProductContext: ChooseProductContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, chooseProductContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
