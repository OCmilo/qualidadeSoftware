export interface IFreight {
  id?: number;
  empresa?: string | null;
  valor?: number | null;
}

export class Freight implements IFreight {
  constructor(public id?: number, public empresa?: string | null, public valor?: number | null) {}
}
