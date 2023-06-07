import { Adresa } from "./adresa.model";

export class Korisnik {
  constructor(
    public tip: string,
    public ime: string,
    public prezime: string,
    public poslovnoIme: string,
    public adresa: Adresa,
    public brojMobilnogTelefona: string,
    public brojFaksa: string,
    public email: string
  ) {
  }

  toXML(): string {
    let XML = "";
    if (this.poslovnoIme === null) {
      XML += `<ks:puno_ime><ks:ime>${this.ime}</ks:ime><ks:prezime>${this.prezime}</ks:prezime></ks:puno_ime>`;
    } else {
      XML += `<ks:poslovno_ime>${this.poslovnoIme}</ks:poslovno_ime>`;
    }
    XML += this.adresa.toXML();
    XML += `<ks:broj_mobilnog_telefona>${this.brojMobilnogTelefona}</ks:broj_mobilnog_telefona>`;
    XML += `<ks:broj_faksa>${this.brojFaksa}</ks:broj_faksa>`;
    XML += `<ks:email>${this.email}</ks:email>`;
    return XML;
  }
}
