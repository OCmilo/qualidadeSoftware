import axios from 'axios';
import { WarrantyFormContext } from './warranty-form.model';

const baseApiUrl = 'api/purchase-process/warranty-form';

export default class WarrantyFormService {
  public loadContext(taskId: number): Promise<WarrantyFormContext> {
    return new Promise<WarrantyFormContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<WarrantyFormContext> {
    return new Promise<WarrantyFormContext>((resolve, reject) => {
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

  public complete(warrantyFormContext: WarrantyFormContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, warrantyFormContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
