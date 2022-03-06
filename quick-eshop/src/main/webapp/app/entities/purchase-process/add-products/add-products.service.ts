import axios from 'axios';
import { AddProductsContext } from './add-products.model';

const baseApiUrl = 'api/purchase-process/add-products';

export default class AddProductsService {
  public loadContext(taskId: number): Promise<AddProductsContext> {
    return new Promise<AddProductsContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<AddProductsContext> {
    return new Promise<AddProductsContext>((resolve, reject) => {
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

  public complete(addProductsContext: AddProductsContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, addProductsContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
