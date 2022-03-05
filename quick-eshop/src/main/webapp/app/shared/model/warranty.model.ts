export interface IWarranty {
  id?: number;
  warrantyDesc?: string | null;
  warrantyMonths?: number | null;
}

export class Warranty implements IWarranty {
  constructor(public id?: number, public warrantyDesc?: string | null, public warrantyMonths?: number | null) {}
}
