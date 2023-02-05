import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { AppConfig } from 'src/app/appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/appConfig/appconfig.service';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PatentService {
  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {}

  sacuvajObrazacIFajlove(
    zahtev: string,
  ) {
   this.sacuvajObrazac(zahtev)
    // this.ucitajFajl(primerDelaFile, false).subscribe((imePrimeraFajla) => {
    //   zahtev += this.kreirajPrimerAutorskogDela(imePrimeraFajla);
    //   if (opisDelaFile) {
    //     this.ucitajFajl(opisDelaFile, true).subscribe((imeOpisaFajla) => {
    //       zahtev += this.kreirajOpisAutorskogDela(imeOpisaFajla);
    //     });
    //   } else {
    //     zahtev += this.kreirajOpisAutorskogDela('');
    //   }
    //   zahtev += '</autorsko_delo>';
    //   zahtev += '</zahtev_za_autorska_prava>';
    //   return this.sacuvajA1Obrazac(zahtev).subscribe((rezultat) => {
    //     console.log('top');
    //     console.log(rezultat);
    //   });
   // });
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
    ).subscribe((response) => console.log(response))
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
}
