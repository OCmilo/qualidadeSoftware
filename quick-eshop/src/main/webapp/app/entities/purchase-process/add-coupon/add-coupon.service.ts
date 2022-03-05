import axios from 'axios';
import { AddCouponContext } from './add-coupon.model';

const baseApiUrl = 'api/purchase-process/add-coupon';

export default class AddCouponService {
  public loadContext(taskId: number): Promise<AddCouponContext> {
    return new Promise<AddCouponContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<AddCouponContext> {
    return new Promise<AddCouponContext>((resolve, reject) => {
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

  public complete(addCouponContext: AddCouponContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, addCouponContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
