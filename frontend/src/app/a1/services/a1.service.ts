import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Inject, Injectable} from '@angular/core';
import {AppConfig} from 'src/app/appConfig/appconfig.interface';
import {APP_SERVICE_CONFIG} from 'src/app/appConfig/appconfig.service';
import {A1} from "../model/A1";
import {MetadataTriplet} from "../../shared/model/MetadataTriplet";


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
    this.ucitajFajl(primerDelaFile, false).subscribe((imePrimeraFajla) => {
      zahtev += this.kreirajPrimerAutorskogDela(imePrimeraFajla);
      if (opisDelaFile) {
        this.ucitajFajl(opisDelaFile, true).subscribe((imeOpisaFajla) => {
          zahtev += this.kreirajOpisAutorskogDela(imeOpisaFajla);
        });
      } else {
        zahtev += this.kreirajOpisAutorskogDela('');
      }
      zahtev += '</autorsko_delo>';
      zahtev += '</zahtev_za_autorska_prava>';
      return this.sacuvajA1Obrazac(zahtev).subscribe((rezultat) => {
        console.log(rezultat);
      });
    });
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
    let a1Zahtev = new A1();
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
}
