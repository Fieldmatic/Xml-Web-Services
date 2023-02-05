import { PatentService } from '../../services/patent.service'
import { DodajPrijavuDijalogComponent } from '../dodaj-prijavu-dijalog/dodaj-prijavu-dijalog.component';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { auto } from '@popperjs/core';
import { Prijava } from '../../model/Prijava';

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
  selector: 'app-patent-obrazac',
  templateUrl: './patent-obrazac.component.html',
  styleUrls: ['./patent-obrazac.component.scss'],
})
export class PatentObrazacComponent implements OnInit {
  patent!: FormGroup;
  ranijePrijave: Prijava[] = [];

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private patentService: PatentService
  ) {}

  ngOnInit(): void {
    this.patent = this.formBuilder.group({
      nazivPronalaska: this.formBuilder.group({
        nazivPronalaskaRS: new FormControl('Srpski pronalazak'),
        nazivPronalaskaENG: new FormControl('Engleski pronalazak'),
      }),
      podnosilac: this.formBuilder.group({
        tipPodnosioca: new FormControl('fizickoLice'),
        email: new FormControl('istevanovic3112@gmail.com'),
        brojTelefona: new FormControl('0656564261'),
        jePronalazac: new FormControl(false),
        ime: new FormControl('Ivana'),
        prezime: new FormControl('Milic'),
        drzavljanstvo: this.formBuilder.group({
          tip: new FormControl('страно'),
          jmbg: new FormControl('3112999185855'),
          brojPasosa: new FormControl('1234567890'),
        }),
        poslovnoIme: new FormControl('milic-prom'),
        brojFaksa: new FormControl('0552502534'),
        adresaPodnosioca: this.formBuilder.group({
          mesto: new FormControl('Bijeljina'),
          ulica: new FormControl('Nikole Tesle'),
          broj: new FormControl('10'),
          drzava: new FormControl('Srbija'),
          postanskiBroj: new FormControl('21000'),
        }),
      }),
      pronalazac: this.formBuilder.group({
        neZeliDaBudeNaveden: new FormControl(false),
        email: new FormControl('istevanovic3112@gmail.com'),
        brojTelefona: new FormControl('0656564261'),
        brojFaksa: new FormControl('0552502534'),
        ime: new FormControl('Ivana'),
        prezime: new FormControl('Milic'),
        adresaPronalazaca: this.formBuilder.group({
          mesto: new FormControl('Bijeljina'),
          ulica: new FormControl('Nikole Tesle'),
          broj: new FormControl('10'),
          drzava: new FormControl('Srbija'),
          postanskiBroj: new FormControl('21000'),
        }),
      }),
      punomocnik: this.formBuilder.group({
        punomocnikZaPrijemPismena: new FormControl(false),
        tipPunomocnika: new FormControl('punomocnik_za_zastupanje'),
        email: new FormControl('istevanovic3112@gmail.com'),
        brojTelefona: new FormControl('0656564261'),
        brojFaksa: new FormControl('0552502534'),
        ime: new FormControl('Ivana'),
        prezime: new FormControl('Stevanovic'),
        adresaPunomocnika: this.formBuilder.group({
          mesto: new FormControl('Novi Sad'),
          ulica: new FormControl('Dr ivana ribara'),
          broj: new FormControl('5'),
          drzava: new FormControl('Srbija'),
          postanskiBroj: new FormControl('21000'),
        }),
      }),
      podacioDostavljanju: this.formBuilder.group({
        nacinDostavljanja: new FormControl('elektronskim_putem'),
        adresa: this.formBuilder.group({
          mesto: new FormControl('Novi Sad'),
          ulica: new FormControl('Dr Ivana ribara'),
          broj: new FormControl('5'),
          drzava: new FormControl('Srbija'),
          postanskiBroj: new FormControl('21000'),
        }),
      }),
      osnovnaPrijava: this.formBuilder.group({
        dopunskaPrijava: new FormControl(false),
        brojPrijave: new FormControl('1'),
        datumPodnosenja: new FormControl()
      }),
    });
  }

  onSubmit() {
    console.log(this.patent.value);
    let zahtev =
      '<?xml version="1.0" encoding="UTF-8"?><zahtev_za_priznanje_patenta xmlns="http://www.xml.tim14.rs/zahtev_za_priznanje_patenta" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ks="http://www.xml.tim14.rs/korisnici">';

    let prijava = this.kreirajPrijavu();

    let pronalazak = this.kreirajPronalazak(this.patent.get('nazivPronalaska').value);

    let podnosilac = this.kreirajPodnosioca(
      this.patent.get('podnosilac').value
    );
    
    let pronalazac = this.kreirajPronalazaca(this.patent.get('pronalazac').value)

    let punomocnik = this.kreirajPunomocnika(
      this.patent.get('punomocnik').value
    );
    let podacioDostavljanju = this.kreirajPodatkeoDostavljanju(this.patent.get('podacioDostavljanju').value)
    
    let osnovnaPrijava = this.kreirajOsnovnuPrijavu(this.patent.get('osnovnaPrijava').value)

    let ranijePrijave = this.kreirajRanijePrijave();

    zahtev += prijava;
    zahtev += pronalazak;
    zahtev += podnosilac;
    zahtev += pronalazac;
    zahtev += punomocnik;
    zahtev += podacioDostavljanju;
    zahtev += osnovnaPrijava;
    zahtev += ranijePrijave;
    zahtev +='</zahtev_za_priznanje_patenta>'

    this.patentService.sacuvajObrazacIFajlove(
      zahtev
    );
  }

  kreirajRanijePrijave() {
    let ranijePrijave = '<ranije_prijave>';
    if (this.ranijePrijave.length > 0) {
      for (let i = 0; i < this.ranijePrijave.length; i++) {
        let prijava: Prijava = this.ranijePrijave[i];
        ranijePrijave += this.kreirajPrijavuSaPodacima(prijava)
    }
  }
    ranijePrijave += '</ranije_prijave>';
    return ranijePrijave;
  }

  kreirajPrijavuSaPodacima(prijava: Prijava): string {
    console.log(prijava.datumPodnosenja)
    let ranijaPrijava = '<ranija_prijava>'
    ranijaPrijava += '<broj_prijave>' + prijava.brojPrijave +'</broj_prijave>'
    ranijaPrijava += '<datum_podnosenja>'+ prijava.datumPodnosenja+'</datum_podnosenja>'
    ranijaPrijava += '<oznaka_drzave>' + prijava.oznakaDrzave +'</oznaka_drzave>'
    ranijaPrijava += '</ranija_prijava>'
    return ranijaPrijava
  }

  kreirajOsnovnuPrijavu(prijavaForm: FormGroup): string {
    if (prijavaForm['dopunskaPrijava']) {
      let osnovnaPrijava = '<osnovna_prijava>'
      osnovnaPrijava += '<broj_prijave>' + prijavaForm['brojPrijave'] + '</broj_prijave>'
      osnovnaPrijava += '<datum_podnosenja>'+ prijavaForm['datumPodnosenja'] +'</datum_podnosenja>'
      osnovnaPrijava+='</osnovna_prijava>'
      return osnovnaPrijava;
    }
    return '';
  }

  kreirajPodatkeoDostavljanju(dostavljanjeForm: FormGroup): string  {
    let podacioDostavljanju = '<podaci_o_dostavljanju>'
    podacioDostavljanju+= '<nacin_dostavljanja>' + dostavljanjeForm['nacinDostavljanja'] + '</nacin_dostavljanja>'
    podacioDostavljanju += this.kreirajAdresu(dostavljanjeForm['adresa']['mesto'], dostavljanjeForm['adresa']['postanskiBroj'],dostavljanjeForm['adresa']['ulica'],dostavljanjeForm['adresa']['broj'],dostavljanjeForm['adresa']['drzava'] )
    podacioDostavljanju += '</podaci_o_dostavljanju>'
    return podacioDostavljanju;
  }

  kreirajPronalazaca(pronalazacForm: FormGroup): string {
    let pronalazac = '<podaci_o_pronalazacu>';
    pronalazac += '<ne_zeli_da_bude_naveden>'+ pronalazacForm['neZeliDaBudeNaveden'] +'</ne_zeli_da_bude_naveden>'
    if (!pronalazacForm['neZeliDaBudeNaveden']) {
      pronalazac+='<pronalazac>'
      pronalazac += this.kreirajAdresu(pronalazacForm['adresaPronalazaca']['mesto'], pronalazacForm['adresaPronalazaca']['postanskiBroj'],pronalazacForm['adresaPronalazaca']['ulica'],pronalazacForm['adresaPronalazaca']['broj'],pronalazacForm['adresaPronalazaca']['drzava'] )
      pronalazac += '<ks:broj_mobilnog_telefona>' + pronalazacForm['brojTelefona'] + '</ks:broj_mobilnog_telefona>'
      pronalazac += '<ks:broj_faksa>' + pronalazacForm['brojFaksa'] + '</ks:broj_faksa>'
      pronalazac += '<ks:email>' + pronalazacForm['email'] + '</ks:email>'
      pronalazac += this.kreirajPunoIme(pronalazacForm['ime'], pronalazacForm['prezime']);
      pronalazac+='</pronalazac>'
    }
    pronalazac+='</podaci_o_pronalazacu>'
    return pronalazac
  }

  kreirajPronalazak(nazivPronalaska: FormGroup): string {
    let pronalazak = '<pronalazak>';
    pronalazak += '<naziv_pronalaska_rs>' + nazivPronalaska['nazivPronalaskaRS'] + '</naziv_pronalaska_rs>';
    pronalazak += '<naziv_pronalaska_eng>' + nazivPronalaska['nazivPronalaskaENG'] + '</naziv_pronalaska_eng>';
    pronalazak+="</pronalazak>";
    return pronalazak;
  }

  kreirajPrijavu(): string {
    let prijava =
      '<prijava><broj_prijave>1</broj_prijave></prijava>';
    return prijava;
  }

  kreirajPodnosioca(podnosilacForm: FormGroup): string {
    let podnosilac = '';
    podnosilac = '<podaci_o_podnosiocu>'
    podnosilac+= '<je_pronalazac>' +podnosilacForm['jePronalazac']+'</je_pronalazac>';
    if (podnosilacForm['tipPodnosioca'] === 'fizickoLice') {
      podnosilac += '<podnosilac xsi:type="ks:TFizicko_Lice">';
      podnosilac += this.kreirajFizickoLice(podnosilacForm);
    } else {
      podnosilac += '<podnosilac xsi:type="ks:TPravno_Lice">';
      podnosilac += this.kreirajPravnoLice(podnosilacForm);
    }
    podnosilac += '</podnosilac>'
    podnosilac += '</podaci_o_podnosiocu>';
    return podnosilac;
  }

  kreirajPunomocnika(punomocnikForm: FormGroup) {
    let punomocnik = '<podaci_o_punomocniku>';
    punomocnik+= '<tip_punomocnika>' +punomocnikForm['tipPunomocnika'] +'</tip_punomocnika>'
    punomocnik += '<punomocnik_za_prijem_pismena>' +punomocnikForm['punomocnikZaPrijemPismena'] +'</punomocnik_za_prijem_pismena>'
    punomocnik += '<punomocnik xsi:type="ks:TFizicko_Lice">';
    punomocnik += this.kreirajAdresu(
      punomocnikForm['adresaPunomocnika']['mesto'],
      punomocnikForm['adresaPunomocnika']['postanskiBroj'],
      punomocnikForm['adresaPunomocnika']['ulica'],
      punomocnikForm['adresaPunomocnika']['broj'],
      punomocnikForm['adresaPunomocnika']['drzava']
    );
    punomocnik += '<ks:broj_mobilnog_telefona>' + punomocnikForm['brojTelefona'] + '</ks:broj_mobilnog_telefona>'
    punomocnik += '<ks:broj_faksa>' + punomocnikForm['brojFaksa'] + '</ks:broj_faksa>'
    punomocnik += '<ks:email>' + punomocnikForm['email'] + '</ks:email>'
    punomocnik += this.kreirajPunoIme(punomocnikForm['ime'], punomocnikForm['prezime']);
    punomocnik += '</punomocnik>';
    punomocnik+= '</podaci_o_punomocniku>';
    return punomocnik;
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
      '<ks:broj_mobilnog_telefona xmlns="http://www.xml.tim14.rs/korisnici">' +
      fizickoLiceForm['brojTelefona'] +
      '</ks:broj_mobilnog_telefona>';
    fizickoLice +=
      '<ks:email xmlns="http://www.xml.tim14.rs/korisnici">' +
      fizickoLiceForm['email'] +
      '</ks:email>';
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
      '<ks:broj_mobilnog_telefona xmlns="http://www.xml.tim14.rs/korisnici">' +
      pravnoLiceForm['brojTelefona'] +
      '</ks:broj_mobilnog_telefona>';
    pravnoLice+=
    '<ks:broj_faksa>'+pravnoLiceForm['brojFaksa'] + '</ks:broj_faksa>'
    pravnoLice +=
      '<ks:email xmlns="http://www.xml.tim14.rs/korisnici">' +
      pravnoLiceForm['email'] +
      '</ks:email>';
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
    let adresa = '<ks:adresa xmlns="http://www.xml.tim14.rs/korisnici">';
    adresa += '<mesto>' + mesto + '</mesto>';
    adresa += '<postanski_broj>' + postanskiBroj + '</postanski_broj>';
    adresa += '<ulica>' + ulica + '</ulica>';
    adresa += '<broj>' + broj + '</broj>';
    adresa += '</ks:adresa>';
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

  dodajRanijuPrijavu() {
    const dialogRef = this.dialog.open(DodajPrijavuDijalogComponent, {
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((prijavaForm) => {
      if (prijavaForm) {
        console.log(prijavaForm);
        this.ranijePrijave.push(prijavaForm);
      }
    });
  }
}
