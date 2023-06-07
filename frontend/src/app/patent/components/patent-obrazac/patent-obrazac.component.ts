import {PatentService} from '../../services/patent.service'
import {DodajPrijavuDijalogComponent} from '../dodaj-prijavu-dijalog/dodaj-prijavu-dijalog.component';
import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {Prijava} from '../../model/Prijava';
import {XonomyService} from "../../services/xonomy.service";
import {PatentObrazac} from '../../model/PatentObrazac';

declare const Xonomy: any;

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
export class PatentObrazacComponent implements OnInit, OnChanges {
  @Input() patentObrazac: PatentObrazac;

  patent!: FormGroup;
  ranijePrijave: Prijava[] = [];

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private patentService: PatentService,
    private xonomyService: XonomyService
  ) {
  }

  ngOnInit(): void {
    this.setupForm();
    this.promeniTipPodnosioca("fizickoLice");
    this.patent.get('podnosilac.tipPodnosioca').valueChanges.subscribe((value) => {
      this.promeniTipPodnosioca(value);
    });
  }

  setupForm() {
    this.patent = this.formBuilder.group({
      nazivPronalaska: this.formBuilder.group({
        nazivPronalaskaRS: new FormControl(this.patentObrazac?.patent.nazivPronalaska.nazivPronalaskaRS || 'Srpski pronalazak'),
        nazivPronalaskaENG: new FormControl(this.patentObrazac?.patent.nazivPronalaska.nazivPronalaskaENG || 'Engleski pronalazak'),
      }),
      podnosilac: this.formBuilder.group({
        tipPodnosioca: new FormControl(this.patentObrazac?.patent.podnosilac?.tipPodnosioca || 'fizickoLice'),
        jePronalazac: new FormControl(this.patentObrazac?.patent.podnosilac?.jePronalazac || false),
      }),
      pronalazac: this.formBuilder.group({
        neZeliDaBudeNaveden: new FormControl(this.patentObrazac?.patent.pronalazac?.neZeliDaBudeNaveden || false),
        email: new FormControl(this.patentObrazac?.patent.pronalazac?.email || 'istevanovic3112@gmail.com'),
        brojTelefona: new FormControl(this.patentObrazac?.patent.pronalazac?.brojTelefona || '0656564261'),
        brojFaksa: new FormControl(this.patentObrazac?.patent.pronalazac?.brojFaksa || '0552502534'),
        ime: new FormControl(this.patentObrazac?.patent.pronalazac?.ime || 'Ivana'),
        prezime: new FormControl(this.patentObrazac?.patent.pronalazac?.prezime || 'Milic'),
        adresaPronalazaca: this.formBuilder.group({
          mesto: new FormControl(this.patentObrazac?.patent.pronalazac?.adresaPronalazaca?.mesto || 'Bijeljina'),
          ulica: new FormControl(this.patentObrazac?.patent.pronalazac?.adresaPronalazaca?.ulica || 'Nikole Tesle'),
          broj: new FormControl(this.patentObrazac?.patent.pronalazac?.adresaPronalazaca?.broj || '10'),
          drzava: new FormControl(this.patentObrazac?.patent.pronalazac?.adresaPronalazaca?.drzava || 'Srbija'),
          postanskiBroj: new FormControl(this.patentObrazac?.patent.pronalazac?.adresaPronalazaca?.postanskiBroj || '21000'),
        }),
      }),
      punomocnik: this.formBuilder.group({
        punomocnikZaPrijemPismena: new FormControl(this.patentObrazac?.patent.punomocnik?.punomocnikZaPrijemPismena || false),
        tipPunomocnika: new FormControl(this.patentObrazac?.patent.punomocnik?.tipPunomocnika || 'punomocnik_za_zastupanje'),
        email: new FormControl(this.patentObrazac?.patent.punomocnik?.email || 'istevanovic3112@gmail.com'),
        brojTelefona: new FormControl(this.patentObrazac?.patent.punomocnik?.brojTelefona || '0656564261'),
        brojFaksa: new FormControl(this.patentObrazac?.patent.punomocnik?.brojFaksa || '0552502534'),
        ime: new FormControl(this.patentObrazac?.patent.punomocnik?.ime || 'Ivana'),
        prezime: new FormControl(this.patentObrazac?.patent.punomocnik?.prezime || 'Stevanovic'),
        adresaPunomocnika: this.formBuilder.group({
          mesto: new FormControl(this.patentObrazac?.patent.punomocnik?.adresaPunomocnika?.mesto || 'Novi Sad'),
          ulica: new FormControl(this.patentObrazac?.patent.punomocnik?.adresaPunomocnika?.ulica || 'Dr ivana ribara'),
          broj: new FormControl(this.patentObrazac?.patent.punomocnik?.adresaPunomocnika?.broj || '5'),
          drzava: new FormControl(this.patentObrazac?.patent.punomocnik?.adresaPunomocnika?.drzava || 'Srbija'),
          postanskiBroj: new FormControl(this.patentObrazac?.patent.punomocnik?.adresaPunomocnika?.postanskiBroj || '21000'),
        }),
      }),
      podacioDostavljanju: this.formBuilder.group({
        nacinDostavljanja: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.nacinDostavljanja || 'elektronskim_putem'),
        adresa: this.formBuilder.group({
          mesto: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.adresa?.mesto || 'Novi Sad'),
          ulica: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.adresa?.ulica || 'Dr Ivana ribara'),
          broj: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.adresa?.broj || '5'),
          drzava: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.adresa?.drzava || 'Srbija'),
          postanskiBroj: new FormControl(this.patentObrazac?.patent.podaciODostavljanju?.adresa?.postanskiBroj || '21000'),
        }),
      }),
      osnovnaPrijava: this.formBuilder.group({
        dopunskaPrijava: new FormControl(this.patentObrazac?.patent.osnovnaPrijava?.dopunskaPrijava || false),
        brojPrijave: new FormControl(this.patentObrazac?.patent.osnovnaPrijava?.brojPrijave || '1'),
        datumPodnosenja: new FormControl(this.patentObrazac?.patent.osnovnaPrijava?.datumPodnosenja),
      }),
    });
    if (this.patentObrazac !== undefined) {
      this.patent.disable();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['patentObrazac']) {
      if (!changes['patentObrazac'].firstChange) {
        this.setupForm();
      }
    }
  }

  promeniTipPodnosioca(value: string) {
    console.log(value)
    if (value === 'pravnoLice') {
      this.setPravnoLice();
    } else {
      this.setFizickoLice();
    }
  }

  setFizickoLice() {
    let element = document.getElementById("podnosilac");
    let specification = this.xonomyService.fizickoLice;
    let xmlString = '<podnosilac></podnosilac>';
    Xonomy.render(xmlString, element, specification);
  }

  setPravnoLice() {
    let element = document.getElementById("podnosilac");
    let specification = this.xonomyService.pravnoLice;
    let xmlString = '<podnosilac></podnosilac>';
    Xonomy.render(xmlString, element, specification);
  }

  onSubmit() {
    console.log(Xonomy.harvest())
    let podnosilacXonomy = Xonomy.harvest();
    let zahtev =
      '<?xml version="1.0" encoding="UTF-8"?><zahtev_za_priznanje_patenta xmlns="http://www.xml.tim14.rs/zahtev_za_priznanje_patenta" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ks="http://www.xml.tim14.rs/korisnici">';

    let prijava = this.kreirajPrijavu();

    let pronalazak = this.kreirajPronalazak(this.patent.get('nazivPronalaska').value);

    let podnosilac = this.kreirajPodnosioca(this.patent.get('podnosilac').value,
      podnosilacXonomy
    );
    console.log(podnosilac)

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
    pronalazak += "</pronalazak>";
    return pronalazak;
  }

  kreirajPrijavu(): string {
    let prijava =
      '<prijava><broj_prijave>1</broj_prijave></prijava>';
    return prijava;
  }

  kreirajPodnosioca(podnosilacForm: FormGroup, podnosilacXonomy: string): string {
    podnosilacXonomy = podnosilacXonomy.replaceAll(" xml:space='preserve'", "");
    console.log(podnosilacXonomy)
    let podnosilac = '';
    podnosilac = '<podaci_o_podnosiocu>'
    podnosilac += '<je_pronalazac>' + podnosilacForm['jePronalazac'] + '</je_pronalazac>';
    if (podnosilacForm['tipPodnosioca'] === 'fizickoLice') {
      podnosilacXonomy = podnosilacXonomy.replace("<podnosilac>", '<podnosilac xsi:type="ks:TFizicko_Lice">');
    } else {
      podnosilacXonomy = podnosilacXonomy.replace("<podnosilac>", '<podnosilac xsi:type="ks:TPravno_Lice">');
    }
    podnosilacXonomy = podnosilacXonomy.replaceAll('adresa', 'ks:adresa')
    podnosilacXonomy = podnosilacXonomy.replaceAll('mesto', 'ks:mesto')
    podnosilacXonomy = podnosilacXonomy.replaceAll('ulica', 'ks:ulica')
    podnosilacXonomy = podnosilacXonomy.replaceAll('<broj>', '<ks:broj>')
    podnosilacXonomy = podnosilacXonomy.replaceAll('</broj>', '</ks:broj>')
    podnosilacXonomy = podnosilacXonomy.replaceAll('postanski_broj', 'ks:postanski_broj')
    podnosilacXonomy = podnosilacXonomy.replaceAll('broj_mobilnog_telefona', 'ks:broj_mobilnog_telefona')
    podnosilacXonomy = podnosilacXonomy.replaceAll('broj_faksa', 'ks:broj_mobilnog_telefona')
    podnosilacXonomy = podnosilacXonomy.replaceAll('drzava', 'ks:drzava')
    podnosilacXonomy = podnosilacXonomy.replaceAll('email', 'ks:email')
    podnosilacXonomy = podnosilacXonomy.replaceAll('puno_ime', 'ks:puno_ime')
    podnosilacXonomy = podnosilacXonomy.replaceAll('<ime>', '<ks:ime>')
    podnosilacXonomy = podnosilacXonomy.replaceAll('</ime>', '</ks:ime>')
    podnosilacXonomy = podnosilacXonomy.replaceAll('prezime', 'ks:prezime')
    podnosilacXonomy = podnosilacXonomy.replaceAll('drzavljanstvo', 'ks:drzavljanstvo')
    podnosilacXonomy = podnosilacXonomy.replaceAll('tip_drzavljanstva', 'ks:tip_drzavljanstva')
    podnosilacXonomy = podnosilacXonomy.replaceAll('jmbg', 'ks:jmbg')
    podnosilacXonomy = podnosilacXonomy.replaceAll('broj_pasosa', 'ks:broj_pasosa')
    podnosilacXonomy = podnosilacXonomy.replaceAll('poslovno_ime', 'ks:poslovno_ime')
    podnosilac += podnosilacXonomy;
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
