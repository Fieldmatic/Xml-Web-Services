import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Inject, Injectable} from '@angular/core';
import {AppConfig} from 'src/app/appConfig/appconfig.interface';
import {APP_SERVICE_CONFIG} from 'src/app/appConfig/appconfig.service';
import {MetadataTriplet} from "../../shared/model/MetadataTriplet";
import {OsnovniPodaciObrascu} from "../../shared/model/OsnovniPodaciObrascu";
import {map} from "rxjs";
import {ToastrService} from "ngx-toastr";

declare var require: any;

@Injectable({
  providedIn: 'root',
})
export class PatentService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private toastrService: ToastrService,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {
  }

  sacuvajObrazacIFajlove(
    zahtev: string,
  ) {
   this.sacuvajObrazac(zahtev)
  }

  sacuvajObrazac(request: string) {
    console.log(request)
    return this.http.post(
      this.config.patentEndpoint + 'patent',
      request,
      {
        observe: 'body',
        responseType: 'text',
        headers: {
          'Content-Type': 'application/xml',
          Accept: 'application/xml',
        },
      }
    ).subscribe((response) => {
      console.log(response)
      this.toastrService.success('Uspešno ste poslali zahtev.', 'Zahtev poslat')
    })
  }

  dobaviSveZahteve(role) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/xml')
      .set('role', role);

    return this.http.get(
      this.config.patentEndpoint + 'patent/getAll',
      {
        headers,
        responseType: 'text',
      }
    );
  }

  dobaviZahtevPoIdu(id: string) {
    return this.http.get(
      this.config.patentEndpoint + 'patent/' + id,
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

  pretraziZahtevePoMetapodacima(triplets: MetadataTriplet[], role) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/xml')
      .set('role', role)
      .set('Accept', 'application/xml');
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
      this.config.patentEndpoint + 'patent/pretragaPoMetapodacima',
      zahtev,
      {
        observe: 'body',
        responseType: 'text',
        headers: headers
      }
    );
  }

  pretraziZahtevePoTekstu(filteri: string[], role) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/xml')
      .set('role', role);
    let zahtev = "<pretraga>"
    for (const filter of filteri) {
      zahtev += "<filteri>" + filter + "</filteri>"
    }
    zahtev += "</pretraga>";
    return this.http.post(
      this.config.patentEndpoint + 'patent/pretragaPoTekstu',
      zahtev,
      {
        headers: headers,
        responseType: 'text',
      }
    );
  }

  kreirajOsnovniPatentOdXmlZahteva(xmlZahtev: any) {
    let patentZahtev = new OsnovniPodaciObrascu();
    let urlIdParts = xmlZahtev['_attributes']['about'].split("/");
    patentZahtev.id = urlIdParts[urlIdParts.length - 1];

    let podnosilac = xmlZahtev['ns3:podaci_o_podnosiocu']['ns3:podnosilac'];
    let tipPodnosioca: string = podnosilac['_attributes']['xsi:type'];
    if (tipPodnosioca === "ns2:TFizicko_Lice") {
      patentZahtev.nazivPodnosioca = podnosilac["ns2:puno_ime"]["ns2:ime"]["_text"] + ' ' + podnosilac["ns2:puno_ime"]["ns2:prezime"]["_text"];
    } else {
      patentZahtev.nazivPodnosioca = podnosilac["ns2:poslovno_ime"]["_text"];
    }

    let datum = xmlZahtev['ns3:prijava']['ns3:datum_podnosenja']['_text'].replace('+', 'T');
    patentZahtev.datumPodnosenja = new Date(datum);

    patentZahtev.status = xmlZahtev['ns3:statusZahteva']['_text'];

    return patentZahtev;
  }

  preuzmiPdf(id: string) {
    return this.http.get(this.config.patentEndpoint + "patent/pdf/" + id, {responseType: 'blob'});
  }

  preuzmiHtml(id: string) {
    return this.http.get(this.config.patentEndpoint + "patent/html/" + id, {responseType: 'blob'});
  }

  preuzmiRdfMetapodatke(id: string) {
    return this.http.get(this.config.patentEndpoint + "patent/rdf/" + id, {
      headers: new HttpHeaders().append('Content-Type', 'application/rdf+xml'),
      responseType: 'blob' as 'json'
    });
  }

  preuzmiJsonMetapodatke(id: string) {
    return this.http.get(this.config.patentEndpoint + "patent/json/" + id, {
      headers: new HttpHeaders().append('Content-Type', 'application/json'),
      responseType: 'blob' as 'json'
    });
  }


}
