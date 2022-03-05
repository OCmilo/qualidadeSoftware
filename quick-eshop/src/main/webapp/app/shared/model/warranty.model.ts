export interface IWarranty {
  id?: number;
  tempo?: string | null;
  valor?: number | null;
}

export class Warranty implements IWarranty {
  constructor(public id?: number, public tempo?: string | null, public valor?: number | null) {}
}
