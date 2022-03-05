export interface IProduct {
  id?: number;
  barcode?: string | null;
  productName?: string | null;
}

export class Product implements IProduct {
  constructor(public id?: number, public barcode?: string | null, public productName?: string | null) {}
}
