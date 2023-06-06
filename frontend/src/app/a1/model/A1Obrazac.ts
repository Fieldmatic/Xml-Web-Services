export class A1Obrazac {
  brojPrijave: string;
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
    this.tipPodnosioca = 'fizickoLice';
    this.email = 'istevanovic3112@gmail.com';
    this.brojTelefona = '0656564261';
    this.ime = 'Ivana';
    this.prezime = 'Milic';
    this.drzavljanstvo = new Drzavljanstvo();
    this.poslovnoIme = 'milic-prom';
    this.adresaPodnosioca = new AdresaPodnosioca();
  }
}

export class Drzavljanstvo {
  tip: string;
  jmbg: string;
  brojPasosa: string;

  constructor() {
    this.tip = 'страно';
    this.jmbg = '3112999185855';
    this.brojPasosa = '1234567890';
  }
}

export class AdresaPodnosioca {
  mesto: string;
  ulica: string;
  broj: string;
  drzava: string;
  postanskiBroj: string;

  constructor() {
    this.mesto = 'Bijeljina';
    this.ulica = 'Nikole Tesle';
    this.broj = '10';
    this.drzava = 'Srbija';
    this.postanskiBroj = '21000';
  }
}

export class Punomocnik {
  prijavaSePodnosiPrekoPunomocnika: boolean;
  ime: string;
  prezime: string;
  adresaPunomocnika: AdresaPunomocnika;

  constructor() {
    this.prijavaSePodnosiPrekoPunomocnika = false;
    this.ime = '';
    this.prezime = '';
    this.adresaPunomocnika = new AdresaPunomocnika();
  }
}

export class AdresaPunomocnika {
  mesto: string;
  ulica: string;
  broj: string;
  drzava: string;
  postanskiBroj: string;

  constructor() {
    this.mesto = '';
    this.ulica = '';
    this.broj = '';
    this.drzava = '';
    this.postanskiBroj = '';
  }
}

export class AutorskoDelo {
  naslov: string;
  vrstaDela: string;
  formaZapisa: string;
  uRadnomOdnosu: boolean;
  nacinKoriscenja: string;
  tipAutora: string;
  originalnoDelo: OriginalnoDelo;
  primer: Primer;
  opis: Opis;
}

export class OriginalnoDelo {
  originalno: boolean;
  naslov: string;

  constructor() {
    this.originalno = true;
    this.naslov = '';
  }
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
