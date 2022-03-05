export interface IProduct {
  id?: number;
  productName?: string | null;
  availableQuantity?: number | null;
  price?: number | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public productName?: string | null,
    public availableQuantity?: number | null,
    public price?: number | null
  ) {}
}
