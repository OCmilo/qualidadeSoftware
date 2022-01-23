export interface IProduct {
  id?: number;
  name?: string | null;
  barcode?: string | null;
  price?: number | null;
  availableQuantity?: number | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string | null,
    public barcode?: string | null,
    public price?: number | null,
    public availableQuantity?: number | null
  ) {}
}
