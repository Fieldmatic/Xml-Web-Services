export class Prijava {
  constructor(
    public broj_prijave: number,
    public datum_podnosenja: Date,
    public sluzbenik: string,
    public prihvacena: boolean
  ) {}
}
