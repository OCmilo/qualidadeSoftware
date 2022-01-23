import { IProduct } from '@/shared/model/product.model';

export interface IPurchaseProcess {
  id?: number;
  processInstance?: any | null;
  product?: IProduct | null;
}

export class PurchaseProcess implements IPurchaseProcess {
  constructor(public id?: number, public processInstance?: any | null, public product?: IProduct | null) {}
}
