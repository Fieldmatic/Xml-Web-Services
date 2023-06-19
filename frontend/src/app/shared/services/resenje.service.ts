import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {APP_SERVICE_CONFIG} from "../../appConfig/appconfig.service";
import {AppConfig} from "../../appConfig/appconfig.interface";

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {

  constructor(private http: HttpClient,
              private router: Router,
              @Inject(APP_SERVICE_CONFIG) private config: AppConfig) { }

  obradiZahtev(data: any, servis: string) {
    let zahtev = "<ObradaZahteva>" +
      "<id>" + data.id + "</id>" +
      "<emailSluzbenika>" + data.emailSluzbenika + "</emailSluzbenika>" +
      "<imeSluzbenika>" + data.imeSluzbenika + "</imeSluzbenika>" +
      "<prezimeSluzbenika>" + data.prezimeSluzbenika + "</prezimeSluzbenika>" +
      "<odbijen>" + data.odbijen + "</odbijen>" +
      "<razlogOdbijanja>" + data.razlogOdbijanja + "</razlogOdbijanja>" +
      "</ObradaZahteva>"

    let endpoint = '';
    if (servis === 'p') {
      endpoint = this.config.patentEndpoint;
    } else if (servis === 'a') {
      endpoint = this.config.autorskoPravoEndpoint;
    } else if (servis === 'z') {
      endpoint = this.config.zigEndpoint;
    }
    return this.http.post(
      endpoint + 'resenje/obradi-zahtev',
      zahtev,
      {
        observe: 'body',
        responseType: 'text',
        headers: {
          'Content-Type': 'application/xml',
          Accept: 'application/xml',
        },
      }
    )
  }
}
