export interface IWarrantyForm {
  id?: number;
  name?: string | null;
  quantity?: number | null;
}

export class WarrantyForm implements IWarrantyForm {
  constructor(public id?: number, public name?: string | null, public quantity?: number | null) {}
}
