import { A1Service } from '../../../services/a1.service';
import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DodajAutoraDijalogComponent } from './dodaj-autora-dijalog/dodaj-autora-dijalog.component';
import {A1Obrazac} from "../../../model/A1Obrazac";

export class Autor {
  preminuliAutor: boolean;
  ime: string;
  prezime: string;
  pseudonim: string;
  godinaSmrti: string;
  drzavljanstvo: {
    tip: string;
    jmbg: string;
    brojPasosa: string;
  };
  adresa: {
    mesto: string;
    ulica: string;
    broj: string;
    drzava: string;
    postanskiBroj: string;
  };
}

@Component({
  selector: 'app-a1-obrazac',
  templateUrl: './a1-obrazac.component.html',
  styleUrls: ['./a1-obrazac.component.scss'],
})
export class A1ObrazacComponent implements OnInit, OnChanges {
  @Input() a1Obrazac: A1Obrazac;

  a1Form!: FormGroup;
  autori: Autor[] = [];
  originalniAutor: Autor;
  opisDelaFile!: File;
  primerDelaFile!: File;

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private a1Service: A1Service,
  ) {}

  ngOnInit(): void {
    this.setupForm()
  }

  setupForm() {
    this.a1Form = this.formBuilder.group({
      podnosilac: this.formBuilder.group({
        tipPodnosioca: new FormControl(this.a1Obrazac?.podnosilac?.tipPodnosioca || 'Fizicko_Lice'),
        email: new FormControl(this.a1Obrazac?.podnosilac?.email || 'istevanovic3112@gmail.com'),
        brojTelefona: new FormControl(this.a1Obrazac?.podnosilac?.brojTelefona || '0656564261'),
        ime: new FormControl(this.a1Obrazac?.podnosilac?.ime || 'Sipak'),
        prezime: new FormControl(this.a1Obrazac?.podnosilac?.prezime || 'Milic'),
        drzavljanstvo: this.formBuilder.group({
          tip: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.tip || 'страно'),
          jmbg: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.jmbg || '3112999185855'),
          brojPasosa: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.brojPasosa || '1234567890'),
        }),
        poslovnoIme: new FormControl(this.a1Obrazac?.podnosilac?.poslovnoIme || 'milic-prom'),
        adresaPodnosioca: this.formBuilder.group({
          mesto: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.mesto || 'Bijeljina'),
          ulica: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.ulica || 'Nikole Tesle'),
          broj: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.broj || '10'),
          drzava: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.drzava || 'Srbija'),
          postanskiBroj: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.postanskiBroj || '21000'),
        }),
      }),
      punomocnik: this.formBuilder.group({
        prijavaSePodnosiPrekoPunomocnika: new FormControl(this.a1Obrazac?.punomocnik?.prijavaSePodnosiPrekoPunomocnika || false),
        ime: new FormControl(this.a1Obrazac?.punomocnik?.ime || ''),
        prezime: new FormControl(this.a1Obrazac?.punomocnik?.prezime || ''),
        adresaPunomocnika: this.formBuilder.group({
          mesto: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.mesto || ''),
          ulica: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.ulica || ''),
          broj: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.broj || ''),
          drzava: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.drzava || ''),
          postanskiBroj: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.postanskiBroj || ''),
        }),
      }),
      autorskoDelo: this.formBuilder.group({
        naslov: new FormControl(this.a1Obrazac?.autorskoDelo?.naslov || 'Covek po imenu Uve'),
        vrstaDela: new FormControl(this.a1Obrazac?.autorskoDelo?.vrstaDela || 'патенти'),
        formaZapisa: new FormControl(this.a1Obrazac?.autorskoDelo?.formaZapisa || 'рукопис'),
        uRadnomOdnosu: new FormControl(this.a1Obrazac?.autorskoDelo?.uRadnomOdnosu || false),
        nacinKoriscenja: new FormControl(this.a1Obrazac?.autorskoDelo?.nacinKoriscenja || 'da se koristi bre'),
        tipAutora: new FormControl(this.a1Obrazac?.autorskoDelo?.tipAutora || 'anonimni'),
        originalnoDelo: this.formBuilder.group({
          originalno: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.originalno || true),
          naslov: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.naslov || 'Covek po imenu Ivee'),
        }),
        primer: this.formBuilder.group({
          putanjaDoPrimera: new FormControl(),
        }),
        opis: this.formBuilder.group({
          putanjaDoOpisa: new FormControl(),
        }),
      }),
    });
    if (this.a1Obrazac !== undefined) {
      this.a1Form.disable();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['a1Obrazac']) {
      if (!changes['a1Obrazac'].firstChange) {
        this.setupForm();
      }
    }
  }

  onSubmit() {
    console.log(this.a1Form.value);
    //drzavu za adresu
    //na adresama ks: dodati
    //fali ti drzavljanstvo kod podnosioca fizicko lice

    const klijent = JSON.parse(localStorage.getItem('userData'));
    //let userId = userData.id;
    let emailKlijenta = 'ivana@gmail.com';

    let zahtev =
      '<?xml version="1.0" encoding="UTF-8"?><zahtev_za_autorska_prava xmlns="http://www.xml.tim14.rs/autorska_prava" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ks="http://www.xml.tim14.rs/korisnici">';

    let prijava = this.kreirajPrijavu();

    let podnosilac = this.kreirajPodnosioca(
      this.a1Form.get('podnosilac').value
    );

    let punomocnik = this.kreirajPunomocnika(
      this.a1Form.get('punomocnik').value
    );
    let autorskoDelo = this.kreirajAutorskoDelo(
      this.a1Form.get('autorskoDelo').value
    );

    zahtev += '<emailKlijenta>' + emailKlijenta + '</emailKlijenta>';
    zahtev += prijava;
    zahtev += podnosilac;
    zahtev += punomocnik;
    zahtev += autorskoDelo;

    this.a1Service.sacuvajObrazacIFajlove(
      zahtev,
      this.primerDelaFile,
      this.opisDelaFile
    );
  }

  sacuvajOpisDela(event) {
    this.opisDelaFile = event.target.files[0];
  }

  sacuvajPrimerDela(event) {
    this.primerDelaFile = event.target.files[0];
  }

  kreirajPrijavu(): string {
    let prijava =
      '<prijava><broj_prijave></broj_prijave><datum_podnosenja></datum_podnosenja></prijava>';
    return prijava;
  }

  kreirajPodnosioca(podnosilacForm: FormGroup): string {
    let podnosilac = '';
    if (podnosilacForm['tipPodnosioca'] === 'Fizicko_Lice') {
      podnosilac += '<podnosilac xsi:type="ks:TFizicko_Lice">';
      podnosilac += this.kreirajFizickoLice(podnosilacForm);
    } else {
      podnosilac += '<podnosilac xsi:type="ks:TPravno_Lice">';
      podnosilac += this.kreirajPravnoLice(podnosilacForm);
    }
    podnosilac += '</podnosilac>';
    return podnosilac;
  }

  kreirajPunomocnika(punomocnikForm: FormGroup) {
    let punomocnik = '';
    if (punomocnikForm['prijavaSePodnosiPrekoPunomocnika']) {
      punomocnik += '<punomocnik>';
      punomocnik += this.kreirajPunoIme(
        punomocnikForm['ime'],
        punomocnikForm['prezime']
      );
      punomocnik += this.kreirajAdresu(
        punomocnikForm['adresaPunomocnika']['mesto'],
        punomocnikForm['adresaPunomocnika']['postanskiBroj'],
        punomocnikForm['adresaPunomocnika']['ulica'],
        punomocnikForm['adresaPunomocnika']['broj'],
        punomocnikForm['adresaPunomocnika']['drzava']
      );
      punomocnik += '</punomocnik>';
    }
    return punomocnik;
  }

  kreirajAutorskoDelo(deloForm: FormGroup): string {
    let autorskoDelo = '<autorsko_delo>';
    autorskoDelo +=
      '<naslov_autorskog_dela>' +
      deloForm['naslov'] +
      '</naslov_autorskog_dela>';
    autorskoDelo +=
      '<vrsta_autorskog_dela>' +
      deloForm['vrstaDela'] +
      '</vrsta_autorskog_dela>';
    autorskoDelo +=
      '<forma_zapisa>' + deloForm['formaZapisa'] + '</forma_zapisa>';
    autorskoDelo += this.kreirajPodatkeOAutorima();
    autorskoDelo += this.kreirajOriginalnoAutorskoDelo(
      deloForm['originalnoDelo']
    );
    autorskoDelo +=
      '<stvoreno_u_radnom_odnosu>' +
      deloForm['uRadnomOdnosu'] +
      '</stvoreno_u_radnom_odnosu>';
    autorskoDelo +=
      '<nacin_koriscenja_autorskog_dela>' +
      deloForm['nacinKoriscenja'] +
      '</nacin_koriscenja_autorskog_dela>';
    return autorskoDelo;
  }

  kreirajFizickoLice(fizickoLiceForm: FormGroup): string {
    let fizickoLice = this.kreirajAdresu(
      fizickoLiceForm['adresaPodnosioca']['mesto'],
      fizickoLiceForm['adresaPodnosioca']['postanskiBroj'],
      fizickoLiceForm['adresaPodnosioca']['ulica'],
      fizickoLiceForm['adresaPodnosioca']['broj'],
      fizickoLiceForm['adresaPodnosioca']['drzava']
    );
    fizickoLice +=
      '<broj_mobilnog_telefona xmlns="http://www.xml.tim14.rs/korisnici">' +
      fizickoLiceForm['brojTelefona'] +
      '</broj_mobilnog_telefona>';
    fizickoLice +=
      '<email xmlns="http://www.xml.tim14.rs/korisnici">' +
      fizickoLiceForm['email'] +
      '</email>';
    fizickoLice += this.kreirajPunoIme(
      fizickoLiceForm['ime'],
      fizickoLiceForm['prezime']
    );
    return fizickoLice;
  }

  kreirajPravnoLice(pravnoLiceForm: FormGroup): string {
    let pravnoLice = this.kreirajAdresu(
      pravnoLiceForm['adresaPodnosioca']['mesto'],
      pravnoLiceForm['adresaPodnosioca']['postanskiBroj'],
      pravnoLiceForm['adresaPodnosioca']['ulica'],
      pravnoLiceForm['adresaPodnosioca']['broj'],
      pravnoLiceForm['adresaPodnosioca']['drzava']
    );
    pravnoLice +=
      '<broj_mobilnog_telefona xmlns="http://www.xml.tim14.rs/korisnici">' +
      pravnoLiceForm['brojTelefona'] +
      '</broj_mobilnog_telefona>';
    pravnoLice +=
      '<email xmlns="http://www.xml.tim14.rs/korisnici">' +
      pravnoLiceForm['email'] +
      '</email>';
    pravnoLice +=
      '<ks:poslovno_ime>' +
      pravnoLiceForm['poslovnoIme'] +
      '</ks:poslovno_ime>';
    return pravnoLice;
  }

  kreirajPunoIme(ime: string, prezime: string): string {
    let punoIme = '<ks:puno_ime>';
    punoIme += '<ks:ime>' + ime + '</ks:ime>';
    punoIme += '<ks:prezime>' + prezime + '</ks:prezime>';
    punoIme += '</ks:puno_ime>';
    return punoIme;
  }

  kreirajAdresu(
    mesto: string,
    postanskiBroj: string,
    ulica: string,
    broj: string,
    drzava: string
  ): string {
    let adresa = '<adresa xmlns="http://www.xml.tim14.rs/korisnici">';
    adresa += '<mesto>' + mesto + '</mesto>';
    adresa += '<postanski_broj>' + postanskiBroj + '</postanski_broj>';
    adresa += '<ulica>' + ulica + '</ulica>';
    adresa += '<broj>' + broj + '</broj>';
    adresa += '<drzava>' + drzava + '</drzava>';
    adresa += '</adresa>';
    return adresa;
  }

  kreirajDrzavljanstvo(tip: string, jmbg: string, brojPasosa: string): string {
    let drzavljanstvo = '<ks:drzavljanstvo>';
    drzavljanstvo += '<ks:tip_drzavljanstva>' + tip + '</ks:tip_drzavljanstva>';
    drzavljanstvo += '<ks:jmbg>' + jmbg + '</ks:jmbg>';
    drzavljanstvo += '<ks:broj_pasosa>' + brojPasosa + '</ks:broj_pasosa>';
    drzavljanstvo += '</ks:drzavljanstvo>';
    return drzavljanstvo;
  }

  kreirajPodatkeOAutorima(): string {
    let podaciOAutorima = '<podaci_o_autorima>';
    for (let i = 0; i < this.autori.length; i++) {
      let autor = this.autori[i];
      podaciOAutorima += this.kreirajAutora(
        autor,
        this.a1Form.get('autorskoDelo').get('tipAutora').value,
        false
      );
    }
    podaciOAutorima += '</podaci_o_autorima>';
    return podaciOAutorima;
  }

  kreirajAutora(
    autorObj: Autor,
    tipAutora: string,
    autorOriginalnogDela: boolean
  ): string {
    let autor = '';
    if (autorOriginalnogDela) autor = '<autor_izvornog_autorskog_dela>';
    else autor = '<autor>';

    if (tipAutora === 'anonimni') {
      autor += '<anonimni_autor>' + true + '</anonimni_autor>';
    } else if (autorObj.preminuliAutor) {
      autor += '<godina_smrti>' + autorObj.godinaSmrti + '</godina_smrti>';
      autor += '<anonimni_autor>' + false + '</anonimni_autor>';
    } else {
      //zivi autor
      autor += '<pseudonim>' + autorObj.pseudonim + '</pseudonim>';
      autor += this.kreirajLicnePodatkeSaPunimImenomIAdresom(autorObj);
      autor += '<anonimni_autor>' + false + '</anonimni_autor>';
    }
    if (autorOriginalnogDela) autor += '</autor_izvornog_autorskog_dela>';
    else autor += '</autor>';
    return autor;
  }

  kreirajLicnePodatkeSaPunimImenom(autorObj: Autor): string {
    let licniPodaci = '<licni_podaci>';
    licniPodaci += this.kreirajPunoIme(autorObj.ime, autorObj.prezime);
    licniPodaci += '</licni_podaci>';
    return licniPodaci;
  }

  kreirajLicnePodatkeSaPunimImenomIAdresom(autorObj: Autor): string {
    let licniPodaci = '<licni_podaci>';
    licniPodaci += this.kreirajAdresu(
      autorObj.adresa.mesto,
      autorObj.adresa.postanskiBroj,
      autorObj.adresa.ulica,
      autorObj.adresa.broj,
      autorObj.adresa.drzava
    );
    licniPodaci += this.kreirajPunoIme(autorObj.ime, autorObj.prezime);
    licniPodaci += this.kreirajDrzavljanstvo(
      autorObj.drzavljanstvo.tip,
      autorObj.drzavljanstvo.jmbg,
      autorObj.drzavljanstvo.brojPasosa
    );
    licniPodaci += '</licni_podaci>';
    return licniPodaci;
  }

  kreirajOriginalnoAutorskoDelo(originalnoDeloForm: FormGroup): string {
    let originalnoDelo = '';
    if (originalnoDeloForm['originalno'] === 'false') {
      originalnoDelo += '<izvorno_autorsko_delo>';
      originalnoDelo +=
        '<naslov_izvornog_autorskog_dela>' +
        originalnoDeloForm['naslov'] +
        '</naslov_izvornog_autorskog_dela>';
      originalnoDelo += this.kreirajAutora(
        this.originalniAutor,
        'poznati',
        true
      );
      originalnoDelo += '</izvorno_autorsko_delo>';
    }
    return originalnoDelo;
  }

  dodajAutora() {
    const dialogRef = this.dialog.open(DodajAutoraDijalogComponent, {
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((autorForm) => {
      if (autorForm) {
        console.log(autorForm);
        this.autori.push(autorForm);
      }
    });
  }

  dodajOriginalnogAutora() {
    const dialogRef = this.dialog.open(DodajAutoraDijalogComponent, {
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((autorForm) => {
      if (autorForm) {
        console.log(autorForm);
        this.originalniAutor = autorForm;
      }
    });
  }
}
