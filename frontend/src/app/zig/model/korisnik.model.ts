import { Adresa } from './adresa.model';

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
  ) {}

  toXML(): string {
    let XML = '';
    if (this.poslovnoIme === null) {
      XML += `<puno_ime><ime>${this.ime}</ime><prezime>${this.prezime}</prezime></puno_ime>`;
    } else {
      XML += `<poslovno_ime>${this.poslovnoIme}</poslovno_ime>`;
    }
    XML += this.adresa.toXML();
    XML += `<broj_mobilnog_telefona>${this.brojMobilnogTelefona}</broj_mobilnog_telefona>`;
    XML += `<broj_faksa>${this.brojFaksa}</broj_faksa>`;
    XML += `<email>${this.email}</email>`;
    return XML;
  }
}
