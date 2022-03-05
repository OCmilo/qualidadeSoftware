export interface IFreight {
  id?: number;
  freighter?: string | null;
  freightPrice?: number | null;
}

export class Freight implements IFreight {
  constructor(public id?: number, public freighter?: string | null, public freightPrice?: number | null) {}
}
