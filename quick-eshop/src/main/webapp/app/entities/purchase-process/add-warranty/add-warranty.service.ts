import axios from 'axios';
import { AddWarrantyContext } from './add-warranty.model';

const baseApiUrl = 'api/purchase-process/add-warranty';

export default class AddWarrantyService {
  public loadContext(taskId: number): Promise<AddWarrantyContext> {
    return new Promise<AddWarrantyContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<AddWarrantyContext> {
    return new Promise<AddWarrantyContext>((resolve, reject) => {
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

  public complete(addWarrantyContext: AddWarrantyContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, addWarrantyContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
