export interface ICoupomForm {
  id?: number;
  nome?: string | null;
  desconto?: number | null;
}

export class CoupomForm implements ICoupomForm {
  constructor(public id?: number, public nome?: string | null, public desconto?: number | null) {}
}
