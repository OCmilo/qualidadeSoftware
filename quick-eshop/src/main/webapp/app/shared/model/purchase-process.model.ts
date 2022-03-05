import { IPurchase } from '@/shared/model/purchase.model';

export interface IPurchaseProcess {
  id?: number;
  processInstance?: any | null;
  purchase?: IPurchase | null;
}

export class PurchaseProcess implements IPurchaseProcess {
  constructor(public id?: number, public processInstance?: any | null, public purchase?: IPurchase | null) {}
}
