import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { AppConfig } from 'src/app/appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/appConfig/appconfig.service';

@Injectable({
  providedIn: 'root',
})
export class A1Service {
  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {}

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
