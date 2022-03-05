export interface ICoupon {
  id?: number;
  couponName?: string | null;
  couponValue?: number | null;
}

export class Coupon implements ICoupon {
  constructor(public id?: number, public couponName?: string | null, public couponValue?: number | null) {}
}
