import axios from 'axios';
import { ProductFormContext } from './product-form.model';

const baseApiUrl = 'api/purchase-process/product-form';

export default class ProductFormService {
  public loadContext(taskId: number): Promise<ProductFormContext> {
    return new Promise<ProductFormContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<ProductFormContext> {
    return new Promise<ProductFormContext>((resolve, reject) => {
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

  public complete(productFormContext: ProductFormContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, productFormContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
