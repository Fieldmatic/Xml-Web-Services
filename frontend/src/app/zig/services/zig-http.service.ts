import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
    return of(null);
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

  getRequestPdf() {
    return of(null);
  }

  getRequestXHtml() {
    return of(null);
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
