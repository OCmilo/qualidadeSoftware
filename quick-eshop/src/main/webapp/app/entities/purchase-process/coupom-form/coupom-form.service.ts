import axios from 'axios';
import { CoupomFormContext } from './coupom-form.model';

const baseApiUrl = 'api/purchase-process/coupom-form';

export default class CoupomFormService {
  public loadContext(taskId: number): Promise<CoupomFormContext> {
    return new Promise<CoupomFormContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<CoupomFormContext> {
    return new Promise<CoupomFormContext>((resolve, reject) => {
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

  public complete(coupomFormContext: CoupomFormContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, coupomFormContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
