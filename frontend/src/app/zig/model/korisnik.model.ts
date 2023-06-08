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

  static parseXML(xml): Korisnik {
    const adresa = Adresa.parseXML(xml["ns2:adresa"]);
    let tip = "fizickoLice";
    let ime = "";
    let prezime = "";
    let poslovnoIme = "";
    if ("ns2:poslovno_ime" in xml) {
      tip = "pravnoLice";
      poslovnoIme = xml["ns2:poslovno_ime"]["_text"];
    } else {
      tip = "fizickoLice";
      ime = xml["ns2:puno_ime"]["ns2:ime"]["_text"];
      prezime = xml["ns2:puno_ime"]["ns2:prezime"]["_text"];
    }
    const broj_faksa = xml["ns2:broj_faksa"]["_text"];
    const broj_mobilnog_telefona = xml["ns2:broj_mobilnog_telefona"]["_text"];
    const email = xml["ns2:email"]["_text"];
    return new Korisnik(tip, ime, prezime, poslovnoIme, adresa, broj_mobilnog_telefona, broj_faksa, email);
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
