import { A1Service } from '../../../services/a1.service';
import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup, Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DodajAutoraDijalogComponent } from './dodaj-autora-dijalog/dodaj-autora-dijalog.component';
import {A1Obrazac} from "../../../model/A1Obrazac";
import {LoggedInUser} from "../../../../auth/model/logged-in-user";
import {AuthService} from "../../../../auth/services/auth.service";

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
  loggedInUser: LoggedInUser = null;

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private a1Service: A1Service,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.authService.loggedInUser.subscribe(user => this.loggedInUser = user);
    this.setupForm()
  }

  setupForm() {
    this.a1Form = this.formBuilder.group({
      podnosilac: this.formBuilder.group({
        tipPodnosioca: new FormControl(this.a1Obrazac?.podnosilac?.tipPodnosioca || 'Fizicko_Lice'),
        email: new FormControl(this.a1Obrazac?.podnosilac?.email || 'istevanovic3112@gmail.com', [Validators.pattern('[^@]+@[^\\.]+\\..+'), Validators.required]),
        brojTelefona: new FormControl(this.a1Obrazac?.podnosilac?.brojTelefona || '0656564261', [Validators.pattern('[+]?[0-9]*'), Validators.minLength(9), Validators.required]),
        ime: new FormControl(this.a1Obrazac?.podnosilac?.ime || 'Marija',  [Validators.pattern('^[a-zA-Z]+$')]),
        prezime: new FormControl(this.a1Obrazac?.podnosilac?.prezime || "Krstanovic", [Validators.pattern('^[a-zA-Z]+$')]),
        drzavljanstvo: this.formBuilder.group({
          tip: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.tip || 'страно'),
          jmbg: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.jmbg || '3112999185855', Validators.pattern('^[0-9]{13}$')),
          brojPasosa: new FormControl(this.a1Obrazac?.podnosilac?.drzavljanstvo?.brojPasosa || '1234567890', [Validators.minLength(8)]),
        }),
        poslovnoIme: new FormControl(this.a1Obrazac?.podnosilac?.poslovnoIme || 'milic-prom'),
        adresaPodnosioca: this.formBuilder.group({
          mesto: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.mesto || 'Bijeljina', Validators.required),
          ulica: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.ulica || 'Nikole Tesle', Validators.required),
          broj: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.broj || '10', Validators.required),
          drzava: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.drzava || 'Srbija', Validators.required),
          postanskiBroj: new FormControl(this.a1Obrazac?.podnosilac?.adresaPodnosioca?.postanskiBroj || '21000', [Validators.pattern('[0-9]{5}'), Validators.required]),
        }),
      }),
      punomocnik: this.formBuilder.group({
        prijavaSePodnosiPrekoPunomocnika: new FormControl(this.a1Obrazac?.punomocnik?.prijavaSePodnosiPrekoPunomocnika || false),
        ime: new FormControl(this.a1Obrazac?.punomocnik?.ime || 'Gordana', [Validators.pattern('^[a-zA-Z]+$')]),
        prezime: new FormControl(this.a1Obrazac?.punomocnik?.prezime || 'Milic', [Validators.pattern('^[a-zA-Z]+$')]),
        adresaPunomocnika: this.formBuilder.group({
          mesto: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.mesto || 'Novi Sad'),
          ulica: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.ulica || 'Nikole Tesle'),
          broj: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.broj || '10'),
          drzava: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.drzava || 'Srbija'),
          postanskiBroj: new FormControl(this.a1Obrazac?.punomocnik?.adresaPunomocnika?.postanskiBroj || '21000', [Validators.pattern('[0-9]{5}')]),
        }),
      }),
      autorskoDelo: this.formBuilder.group({
        naslov: new FormControl(this.a1Obrazac?.autorskoDelo?.naslov || 'Covek po imenu Uve'),
        vrstaDela: new FormControl(this.a1Obrazac?.autorskoDelo?.vrstaDela || 'патенти'),
        formaZapisa: new FormControl(this.a1Obrazac?.autorskoDelo?.formaZapisa || 'рукопис'),
        uRadnomOdnosu: new FormControl(this.a1Obrazac?.autorskoDelo?.uRadnomOdnosu || false),
        nacinKoriscenja: new FormControl(this.a1Obrazac?.autorskoDelo?.nacinKoriscenja || 'Da se koristi', [Validators.required]),
        tipAutora: new FormControl(this.a1Obrazac?.autorskoDelo?.tipAutora || 'anonimni'),
        originalnoDelo: this.formBuilder.group({
          originalno: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.originalno?.toString() || 'true'),
          naslov: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.naslov || 'Covek po imenu Uve'),
          imeOriginalnogAutora: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.imeOriginalnogAutora),
          prezimeOriginalnogAutora: new FormControl(this.a1Obrazac?.autorskoDelo?.originalnoDelo?.prezimeOriginalnogAutora),
        }),
        primer: this.formBuilder.group({
          putanjaDoPrimera: new FormControl('', Validators.required),
        }),
        opis: this.formBuilder.group({
          putanjaDoOpisa: new FormControl(),
        }),
      }),
    });
    if (this.a1Obrazac !== undefined) {
      this.autori = this.a1Obrazac?.autorskoDelo?.autori;
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
    if (this.a1Form.valid) {
      let emailKlijenta = this.loggedInUser.email;

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
    fizickoLice += this.kreirajDrzavljanstvo(fizickoLiceForm['drzavljanstvo']['tip'], fizickoLiceForm['drzavljanstvo']['jmbg'], fizickoLiceForm['drzavljanstvo']['brojPasosa']);
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
    if (this.autori.length === 0) {
      podaciOAutorima += this.kreirajAnonimnogAutora(this.a1Form.get('autorskoDelo').get('tipAutora').value);
    }
    for (let i = 0; i < this.autori.length; i++) {
      let autor = this.autori[i];
      podaciOAutorima += this.kreirajAutora(autor);
    }
    podaciOAutorima += '</podaci_o_autorima>';
    return podaciOAutorima;
  }

  kreirajAnonimnogAutora(tipAutora: string) {
    let podaciOAutorima =  '<autor><licni_podaci>\n' +
      '                    <ks:puno_ime>\n' +
      '                        <ks:ime></ks:ime>\n' +
      '                        <ks:prezime></ks:prezime>\n' +
      '                    </ks:puno_ime>\n' +
      '                </licni_podaci>';
    if (tipAutora === "anonimni") {
      podaciOAutorima += '<anonimni_autor>true</anonimni_autor></autor>';
    } else if (tipAutora === "podnosilac") {
      podaciOAutorima += '<anonimni_autor>false</anonimni_autor><podnosilac>true</podnosilac></autor>';
    }
    return podaciOAutorima;
  }

  kreirajAutora(
    autorObj: Autor,
  ): string {
    let autor = '<autor>';
    if (autorObj.preminuliAutor) {
      autor += '<pseudonim>' + autorObj.pseudonim + '</pseudonim>';
      autor += this.kreirajLicnePodatkeSaPunimImenom(autorObj.ime, autorObj.prezime);
      autor += '<godina_smrti>' + autorObj.godinaSmrti + '</godina_smrti>';
      autor += '<anonimni_autor>' + false + '</anonimni_autor>';
    } else {
      autor += '<pseudonim>' + autorObj.pseudonim + '</pseudonim>';
      autor += this.kreirajLicnePodatkeSaPunimImenomIAdresom(autorObj);
      autor += this.kreirajDrzavljanstvo(autorObj.drzavljanstvo.tip, autorObj.drzavljanstvo.jmbg, autorObj.drzavljanstvo.brojPasosa);
      autor += '<anonimni_autor>' + false + '</anonimni_autor>';
    }
    autor += '</autor>';
    return autor;
  }

  kreirajOriginalnogAutora(imeAutora: string, prezimeAutora: string): string {
    let autor = '<autor_izvornog_autorskog_dela>';
    autor += this.kreirajLicnePodatkeSaPunimImenom(imeAutora, prezimeAutora);
    autor += '<anonimni_autor>' + false + '</anonimni_autor>';
    autor += '</autor_izvornog_autorskog_dela>';
    return autor;
  }

  kreirajLicnePodatkeSaPunimImenom(ime: string, prezime: string): string {
    let licniPodaci = '<licni_podaci>';
    licniPodaci += this.kreirajPunoIme(ime, prezime);
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
      originalnoDelo += this.kreirajOriginalnogAutora(originalnoDeloForm['imeOriginalnogAutora'], originalnoDeloForm['prezimeOriginalnogAutora']);
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
        this.originalniAutor = autorForm;
      }
    });
  }
}
