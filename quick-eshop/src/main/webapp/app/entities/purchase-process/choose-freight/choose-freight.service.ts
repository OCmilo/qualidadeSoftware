import axios from 'axios';
import { ChooseFreightContext } from './choose-freight.model';

const baseApiUrl = 'api/purchase-process/choose-freight';

export default class ChooseFreightService {
  public loadContext(taskId: number): Promise<ChooseFreightContext> {
    return new Promise<ChooseFreightContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<ChooseFreightContext> {
    return new Promise<ChooseFreightContext>((resolve, reject) => {
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

  public complete(chooseFreightContext: ChooseFreightContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, chooseFreightContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
