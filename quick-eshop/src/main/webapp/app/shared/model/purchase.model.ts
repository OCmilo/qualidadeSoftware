import { ICoupon } from '@/shared/model/coupon.model';
import { IFreight } from '@/shared/model/freight.model';
import { IProduct } from '@/shared/model/product.model';
import { IWarranty } from '@/shared/model/warranty.model';

export interface IPurchase {
  id?: number;
  userName?: string | null;
  userEmail?: string | null;
  address?: string | null;
  quantity?: number | null;
  confirmation?: boolean | null;
  withWarranty?: boolean | null;
  withCoupon?: boolean | null;
  addProducts?: boolean | null;
  coupon?: ICoupon | null;
  freight?: IFreight | null;
  product?: IProduct | null;
  warranty?: IWarranty | null;
}

export class Purchase implements IPurchase {
  constructor(
    public id?: number,
    public userName?: string | null,
    public userEmail?: string | null,
    public address?: string | null,
    public quantity?: number | null,
    public confirmation?: boolean | null,
    public withWarranty?: boolean | null,
    public withCoupon?: boolean | null,
    public addProducts?: boolean | null,
    public coupon?: ICoupon | null,
    public freight?: IFreight | null,
    public product?: IProduct | null,
    public warranty?: IWarranty | null
  ) {
    this.confirmation = this.confirmation ?? false;
    this.withWarranty = this.withWarranty ?? false;
    this.withCoupon = this.withCoupon ?? false;
    this.addProducts = this.addProducts ?? false;
  }
}
