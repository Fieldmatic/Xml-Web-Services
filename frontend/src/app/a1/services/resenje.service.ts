import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {APP_SERVICE_CONFIG} from "../../appConfig/appconfig.service";
import {AppConfig} from "../../appConfig/appconfig.interface";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {

  constructor(private http: HttpClient,
              private router: Router,
              @Inject(APP_SERVICE_CONFIG) private config: AppConfig) { }

  obradiZahtev(data: any) {
    let zahtev = "<ObradaZahteva>" +
                      "<id>" + data.id + "</id>" +
                      "<emailSluzbenika>" + data.emailSluzbenika + "</emailSluzbenika>" +
                      "<imeSluzbenika>" + data.imeSluzbenika + "</imeSluzbenika>" +
                      "<prezimeSluzbenika>" + data.prezimeSluzbenika + "</prezimeSluzbenika>" +
                      "<odbijen>" + data.odbijen + "</odbijen>" +
                      "<razlogOdbijanja>" + data.razlogOdbijanja + "</razlogOdbijanja>" +
                      "</ObradaZahteva>"
    console.log(zahtev)
    return this.http.post(
      this.config.autorskoPravoEndpoint + 'resenje/obradi-zahtev',
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
