import { ICoupon } from '@/shared/model/coupon.model';
import { IFreight } from '@/shared/model/freight.model';
import { IProduct } from '@/shared/model/product.model';
import { IWarranty } from '@/shared/model/warranty.model';

export interface IPurchase {
  id?: number;
  userName?: string | null;
  userAddress?: string | null;
  quantity?: number | null;
  confirmacao?: boolean | null;
  withCoupon?: boolean | null;
  withWarranty?: boolean | null;
  coupon?: ICoupon | null;
  freight?: IFreight | null;
  product?: IProduct | null;
  warranty?: IWarranty | null;
}

export class Purchase implements IPurchase {
  constructor(
    public id?: number,
    public userName?: string | null,
    public userAddress?: string | null,
    public quantity?: number | null,
    public confirmacao?: boolean | null,
    public withCoupon?: boolean | null,
    public withWarranty?: boolean | null,
    public coupon?: ICoupon | null,
    public freight?: IFreight | null,
    public product?: IProduct | null,
    public warranty?: IWarranty | null
  ) {
    this.confirmacao = this.confirmacao ?? false;
    this.withCoupon = this.withCoupon ?? false;
    this.withWarranty = this.withWarranty ?? false;
  }
}
