export interface ICoupon {
  id?: number;
  couponName?: string | null;
  couponDiscount?: number | null;
}

export class Coupon implements ICoupon {
  constructor(public id?: number, public couponName?: string | null, public couponDiscount?: number | null) {}
}
