import {Autor} from "../components/a1-container/a1-obrazac/a1-obrazac.component";

export class A1Obrazac {
  brojPrijave: string;
  statusZahteva: string;
  podnosilac: Podnosilac;
  punomocnik: Punomocnik;
  autorskoDelo: AutorskoDelo;

  constructor() {
    this.podnosilac = new Podnosilac();
    this.punomocnik = new Punomocnik();
    this.autorskoDelo = new AutorskoDelo();
  }
}

export class Podnosilac {
  tipPodnosioca: string;
  email: string;
  brojTelefona: string;
  ime: string;
  prezime: string;
  drzavljanstvo: Drzavljanstvo;
  poslovnoIme: string;
  adresaPodnosioca: AdresaPodnosioca;

  constructor() {
    this.drzavljanstvo = new Drzavljanstvo();
    this.adresaPodnosioca = new AdresaPodnosioca();
  }
}

export class Drzavljanstvo {
  public tip: string;
  public jmbg: string;
  public brojPasosa: string;

}

export class AdresaPodnosioca {
  mesto: string;
  ulica: string;
  broj: string;
  drzava: string;
  postanskiBroj: string;
}

export class Punomocnik {
  prijavaSePodnosiPrekoPunomocnika: boolean;
  ime: string;
  prezime: string;
  adresaPunomocnika: AdresaPunomocnika;

  constructor() {
    this.adresaPunomocnika = new AdresaPunomocnika();
  }
}

export class AdresaPunomocnika {
  mesto: string;
  ulica: string;
  broj: string;
  drzava: string;
  postanskiBroj: string;
}

export class AutorskoDelo {
  naslov: string;
  vrstaDela: string;
  formaZapisa: string;
  uRadnomOdnosu: boolean;
  nacinKoriscenja: string;
  tipAutora: string;
  autori: Autor[];
  originalnoDelo: OriginalnoDelo;
  primer: Primer;
  opis: Opis;
}

export class OriginalnoDelo {
  originalno: boolean;
  naslov: string;
  imeOriginalnogAutora: string;
  prezimeOriginalnogAutora: string;
}

export class Primer {
  putanjaDoPrimera: string;

  constructor() {
    this.putanjaDoPrimera = '';
  }
}

export class Opis {
  putanjaDoOpisa: string;

  constructor() {
    this.putanjaDoOpisa = '';
  }
}
