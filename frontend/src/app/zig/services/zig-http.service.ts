import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { of } from 'rxjs';
import { APP_SERVICE_CONFIG } from '../../appConfig/appconfig.service';
import { AppConfig } from '../../appConfig/appconfig.interface';

// TODO: create http requests
@Injectable({
  providedIn: 'root',
})
export class ZigHttpService {
  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getAllRequests() {
    return this.http.get(this.config.zigEndpoint + 'all', {
      headers: new HttpHeaders().append('Accept', 'application/xml'),
      responseType: 'text',
    });
  }

  getRequest() {
    return of(null);
  }

  getAllByMetadata() {
    return of(null);
  }

  getReferencedDocument() {
    return of(null);
  }

  getRequestPdf(id: string) {
    return this.http.get<any>(this.config.zigEndpoint + 'pdf', {
      params: new HttpParams().append('id', id),
      responseType: 'arraybuffer' as 'json',
    });
  }

  getRequestXHtml(id: string) {
    return this.http.get<any>(this.config.zigEndpoint + 'html', {
      params: new HttpParams().append('id', id),
      responseType: 'arraybuffer' as 'json',
    });
  }

  getRequestMetadataRdf() {
    return of(null);
  }

  getRequestMetadataJson() {
    return of(null);
  }

  getReport() {
    return of(null);
  }

  postRequest() {
    return of(null);
  }

  putAcceptRequest() {
    return of(null);
  }

  putRejectRequest() {
    return of(null);
  }
}
