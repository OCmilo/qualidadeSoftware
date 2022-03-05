import axios from 'axios';
import { FreightFormContext } from './freight-form.model';

const baseApiUrl = 'api/purchase-process/freight-form';

export default class FreightFormService {
  public loadContext(taskId: number): Promise<FreightFormContext> {
    return new Promise<FreightFormContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<FreightFormContext> {
    return new Promise<FreightFormContext>((resolve, reject) => {
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

  public complete(freightFormContext: FreightFormContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, freightFormContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
