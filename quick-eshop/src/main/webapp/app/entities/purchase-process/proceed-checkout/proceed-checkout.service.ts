import axios from 'axios';
import { ProceedCheckoutContext } from './proceed-checkout.model';

const baseApiUrl = 'api/purchase-process/proceed-checkout';

export default class ProceedCheckoutService {
  public loadContext(taskId: number): Promise<ProceedCheckoutContext> {
    return new Promise<ProceedCheckoutContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<ProceedCheckoutContext> {
    return new Promise<ProceedCheckoutContext>((resolve, reject) => {
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

  public complete(proceedCheckoutContext: ProceedCheckoutContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, proceedCheckoutContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
