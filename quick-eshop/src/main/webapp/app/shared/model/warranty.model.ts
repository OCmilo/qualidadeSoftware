export interface IWarranty {
  id?: number;
  warrantyDescription?: string | null;
  warrantyMonths?: number | null;
}

export class Warranty implements IWarranty {
  constructor(public id?: number, public warrantyDescription?: string | null, public warrantyMonths?: number | null) {}
}
