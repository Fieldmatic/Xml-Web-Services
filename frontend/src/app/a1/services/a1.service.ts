import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Inject, Injectable} from '@angular/core';
import {AppConfig} from 'src/app/appConfig/appconfig.interface';
import {APP_SERVICE_CONFIG} from 'src/app/appConfig/appconfig.service';
import {MetadataTriplet} from "../../shared/model/MetadataTriplet";
import {concatMap, map, of, tap} from "rxjs";
import {A1Obrazac, AutorskoDelo, Opis, Podnosilac, Primer} from "../model/A1Obrazac";
import {OsnovniPodaciObrascu} from "../../shared/model/OsnovniPodaciObrascu";

declare var require: any;

@Injectable({
  providedIn: 'root',
})
export class A1Service {
  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {}

  sacuvajObrazacIFajlove(
    zahtev: string,
    primerDelaFile: File,
    opisDelaFile: File
  ) {
    this.ucitajFajl(primerDelaFile, false).pipe(
      concatMap((imePrimeraFajla) => {
        zahtev += this.kreirajPrimerAutorskogDela(imePrimeraFajla);
        if (opisDelaFile) {
          return this.ucitajFajl(opisDelaFile, true).pipe(
            map((imeOpisaFajla) => {
              zahtev += this.kreirajOpisAutorskogDela(imeOpisaFajla);
              return zahtev;
            })
          );
        } else {
          zahtev += this.kreirajOpisAutorskogDela('');
          return of(zahtev);
        }
      }),
      map((zahtev) => {
        zahtev += '</autorsko_delo>';
        zahtev += '</zahtev_za_autorska_prava>';
        return zahtev;
      }),
      concatMap((zahtev) => {
        return this.sacuvajA1Obrazac(zahtev).pipe(
          tap((rezultat) => {
            console.log(rezultat);
          })
        );
      })
    ).subscribe();
  }

  sacuvajA1Obrazac(request: string) {
    return this.http.post(
      this.config.autorskoPravoEndpoint + 'autorska-prava',
      request,
      {
        observe: 'body',
        responseType: 'text',
        headers: {
          'Content-Type': 'application/xml',
          Accept: 'application/xml',
        },
      }
    );
  }

  ucitajFajl(file: File, type: boolean) {
    const exampleFormData = new FormData();
    exampleFormData.append('file', <File>file, (<File>file).name);
    return this.http.post(
      this.config.autorskoPravoEndpoint + 'autorska-prava/uploadFile/' + type,
      exampleFormData,
      {
        observe: 'body',
        responseType: 'text',
      }
    );
  }

  getAllZahtevi() {
    return this.http.get(
      this.config.autorskoPravoEndpoint + 'autorska-prava/getAll',
      {
        headers: new HttpHeaders().set('Content-Type', 'application/xml'),
        responseType: 'text',
      }
    );
  }

  dobaviZahtevPoIdu(id: string) {
    return this.http.get(
      this.config.autorskoPravoEndpoint + 'autorska-prava/' + id,
      {
        headers: new HttpHeaders().set('Content-Type', 'application/xml'),
        responseType: 'text',
      }
    ).pipe(map(response => {
      let xmlResult = require('xml-js').xml2json(response, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      return JSON.parse(xmlResult);
    }))
  }

  pretraziZahtevePoMetapodacima(triplets: MetadataTriplet[]) {
    let zahtev = "<metadata>"
    for (const triplet of triplets) {
      zahtev += "<triplet>" +
        "<predikat>" + triplet.predikat + "</predikat>"
        + "<objekat>" + triplet.objekat + "</objekat>"
        + "<operator>" + triplet.operator + "</operator>"
        + "</triplet>"
    }
    zahtev += "</metadata>";
    return this.http.post(
      this.config.autorskoPravoEndpoint + 'autorska-prava/pretragaPoMetapodacima',
      zahtev,
      {
        observe: 'body',
        responseType: 'text',
        headers: {
          'Content-Type': 'application/xml',
          Accept: 'application/xml',
        },
      }
    );
  }

  kreirajPrimerAutorskogDela(putanjaDoPrimera: string): string {
    let primer = '<primer_autorskog_dela>';
    primer +=
      '<putanja_do_primera>' + putanjaDoPrimera + '</putanja_do_primera>';
    primer += '<tip_primera>слика</tip_primera>';
    primer += '</primer_autorskog_dela>';
    return primer;
  }

  kreirajOpisAutorskogDela(opisDela: string): string {
    let opis =
      ' <opis_autorskog_dela><putanja_do_opisa>' +
      opisDela +
      '</putanja_do_opisa></opis_autorskog_dela>';
    return opis;
  }

  kreirajA1ZahtevOdXmlZahteva(xmlZahtev: any) {
    let a1Zahtev = new OsnovniPodaciObrascu();
    a1Zahtev.id = xmlZahtev['ns3:idZahteva']['_text']

    let podnosilac = xmlZahtev['ns3:podnosilac']
    let tipPodnosioca: string = podnosilac['_attributes']['xsi:type']
    if (tipPodnosioca === "ns2:TFizicko_Lice") {
      a1Zahtev.nazivPodnosioca = podnosilac["ns2:puno_ime"]["ns2:ime"]['_text'] + ' ' + podnosilac["ns2:puno_ime"]["ns2:prezime"]['_text']
    } else {
      a1Zahtev.nazivPodnosioca = podnosilac["ns2:poslovnoIme"]["_text"]
    }
    let datum = xmlZahtev['ns3:prijava']['ns3:datum_podnosenja']['_text'].replace('+', 'T')
    a1Zahtev.datumPodnosenja = new Date(datum)

    a1Zahtev.status = xmlZahtev['ns3:statusZahteva']['_text']
    return a1Zahtev;
  }

  pretraziZahtevePoTekstu(filteri: string[]) {
    let zahtev = "<pretraga>"
    for (const filter of filteri) {
      zahtev += "<filteri>" + filter + "</filteri>"
    }
    zahtev += "</pretraga>";
    return this.http.post(
      this.config.autorskoPravoEndpoint + 'autorska-prava/pretragaPoTekstu',
      zahtev,
      {
        headers: new HttpHeaders().set('Content-Type', 'application/xml'),
        responseType: 'text',
      }
    );
  }

  preuzmiPdf(id: string) {
    return this.http.get(this.config.autorskoPravoEndpoint + "autorska-prava/pdf/" + id, {responseType: 'blob'});
  }

  preuzmiHtml(id: string) {
    return this.http.get(this.config.autorskoPravoEndpoint + "autorska-prava/html/" + id, {responseType: 'blob'});
  }

  preuzmiRdfMetapodatke(id: string) {
    return this.http.get(this.config.autorskoPravoEndpoint + "autorska-prava/rdf/" + id, {
      headers: new HttpHeaders().append('Content-Type', 'application/rdf+xml'),
      responseType: 'blob' as 'json'
    });
  }

  preuzmiJsonMetapodatke(id: string) {
    return this.http.get(this.config.autorskoPravoEndpoint + "autorska-prava/json/" + id, {
      headers: new HttpHeaders().append('Content-Type', 'application/json'),
      responseType: 'blob' as 'json'
    });
  }

  mapirajObjekatA1(objekat: any): A1Obrazac {
    const a1 = new A1Obrazac();

    if (objekat.zahtev_za_autorska_prava) {
      const zahtev = objekat.zahtev_za_autorska_prava;
      a1.brojPrijave = zahtev['prijava']['broj_prijave']._text
      a1.statusZahteva = zahtev['statusZahteva']._text
      a1.podnosilac = new Podnosilac();
      if (zahtev.podnosilac) {
        const podnosilac = zahtev.podnosilac;
        a1.podnosilac.tipPodnosioca = podnosilac["_attributes"]["xsi:type"].replace("ns2:T", "");
        a1.podnosilac.email = podnosilac["ns2:email"]._text;
        a1.podnosilac.brojTelefona = podnosilac["ns2:broj_mobilnog_telefona"]._text;
        a1.podnosilac.ime = podnosilac["ns2:puno_ime"]["ns2:ime"]._text;
        a1.podnosilac.prezime = podnosilac["ns2:puno_ime"]["ns2:prezime"]._text;

        if (podnosilac["ns2:adresa"]) {
          const adresa = podnosilac["ns2:adresa"];
          a1.podnosilac.adresaPodnosioca.mesto = adresa["ns2:mesto"]._text;
          a1.podnosilac.adresaPodnosioca.ulica = adresa["ns2:ulica"]._text;
          a1.podnosilac.adresaPodnosioca.broj = adresa["ns2:broj"]._text;
          a1.podnosilac.adresaPodnosioca.postanskiBroj = adresa["ns2:postanski_broj"]._text;
          a1.podnosilac.adresaPodnosioca.drzava = adresa["ns2:drzava"]._text;
        }
      }

      a1.autorskoDelo = new AutorskoDelo();
      if (zahtev.autorsko_delo) {
        const autorskoDelo = zahtev.autorsko_delo;
        a1.autorskoDelo.naslov = autorskoDelo["naslov_autorskog_dela"]._text;
        a1.autorskoDelo.vrstaDela = autorskoDelo["vrsta_autorskog_dela"]._text;
        a1.autorskoDelo.formaZapisa = autorskoDelo["forma_zapisa"]._text;
        a1.autorskoDelo.uRadnomOdnosu = autorskoDelo["stvoreno_u_radnom_odnosu"]._text === "true";
        a1.autorskoDelo.nacinKoriscenja = autorskoDelo["nacin_koriscenja_autorskog_dela"]._text;

        a1.autorskoDelo.primer = new Primer();
        if (autorskoDelo["primer_autorskog_dela"]) {
          const primer = autorskoDelo["primer_autorskog_dela"];
          a1.autorskoDelo.primer.putanjaDoPrimera = primer["putanja_do_primera"]._text;
        }

        a1.autorskoDelo.opis = new Opis();
        if (autorskoDelo["opis_autorskog_dela"]) {
          const opis = autorskoDelo["opis_autorskog_dela"];
          a1.autorskoDelo.opis.putanjaDoOpisa = opis["putanja_do_opisa"]._text;
        }
      }
    }
    return a1;
  }
}
