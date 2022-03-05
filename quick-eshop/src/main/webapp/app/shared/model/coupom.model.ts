export interface ICoupom {
  id?: number;
  nome?: string | null;
  desconto?: number | null;
}

export class Coupom implements ICoupom {
  constructor(public id?: number, public nome?: string | null, public desconto?: number | null) {}
}
