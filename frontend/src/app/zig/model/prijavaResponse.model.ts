export class PrijavaResponse {
  constructor(
    public brojZahteva: string,
    public datumPodnosenja: Date,
    public podnosilac: string,
    public status: string,
    public sluzbenik: string
  ) {}
}
