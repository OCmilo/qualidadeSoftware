export interface IFreightForm {
  id?: number;
  name?: string | null;
  quantity?: number | null;
}

export class FreightForm implements IFreightForm {
  constructor(public id?: number, public name?: string | null, public quantity?: number | null) {}
}
