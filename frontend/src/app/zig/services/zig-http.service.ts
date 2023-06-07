import { Inject, Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { forkJoin, mergeMap, of } from "rxjs";
import { APP_SERVICE_CONFIG } from "../../appConfig/appconfig.service";
import { AppConfig } from "../../appConfig/appconfig.interface";
import { ZahtevZaPriznanjeZiga } from "../model/zahtev-za-priznanje-ziga.model";
import { FileDTO } from "../model/FileDTO";

@Injectable({
  providedIn: "root"
})
export class ZigHttpService {
  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {
  }

  getAllRequests() {
    return this.http.get(this.config.zigEndpoint + "all", {
      headers: new HttpHeaders().append("Accept", "application/xml"),
      responseType: "text"
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
    return this.http.get<any>(this.config.zigEndpoint + "pdf", {
      params: new HttpParams().append("id", id),
      responseType: "arraybuffer" as "json"
    });
  }

  getRequestXHtml(id: string) {
    return this.http.get<any>(this.config.zigEndpoint + "html", {
      params: new HttpParams().append("id", id),
      responseType: "arraybuffer" as "json"
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

  postRequest(zahtevZaPriznanjeZiga: ZahtevZaPriznanjeZiga) {
    const uploadObservables = [];

    if (zahtevZaPriznanjeZiga.punomocje.punomocje?.dokument) {
      uploadObservables.push(
        this.uploadFile(zahtevZaPriznanjeZiga.punomocje.punomocje, "PUNOMOCJE").pipe(
          mergeMap(response => {
            zahtevZaPriznanjeZiga.punomocje.punomocje.naziv = response;
            return of(response);
          })
        )
      );
    }

    uploadObservables.push(
      this.uploadFile(zahtevZaPriznanjeZiga.spisakRobeIUsluga, "SPISAK_ROBE").pipe(
        mergeMap(response => {
          zahtevZaPriznanjeZiga.spisakRobeIUsluga.naziv = response;
          return of(response);
        })
      )
    );

    uploadObservables.push(
      this.uploadFile(zahtevZaPriznanjeZiga.znak.izgled, "IZGLED_ZNAKA").pipe(
        mergeMap(response => {
          zahtevZaPriznanjeZiga.znak.izgled.naziv = response;
          return of(response);
        })
      )
    );

    uploadObservables.push(
      this.uploadFile(zahtevZaPriznanjeZiga.takse.dokazOUplati, "DOKAZ_O_UPLATI").pipe(
        mergeMap(response => {
          zahtevZaPriznanjeZiga.takse.dokazOUplati.naziv = response;
          return of(response);
        })
      )
    );

    if (zahtevZaPriznanjeZiga.pravoPrvenstva.zatrazeno && zahtevZaPriznanjeZiga.pravoPrvenstva.dokaz?.dokument) {
      uploadObservables.push(
        this.uploadFile(zahtevZaPriznanjeZiga.pravoPrvenstva.dokaz, "DOKAZ_PRAVA_PRVENSTVA").pipe(
          mergeMap(response => {
            zahtevZaPriznanjeZiga.pravoPrvenstva.dokaz.naziv = response;
            return of(response);
          })
        )
      );
    }

    if (zahtevZaPriznanjeZiga.opstiAktOKolektivnomZiguIliZiguGarancije) {
      uploadObservables.push(
        this.uploadFile(zahtevZaPriznanjeZiga.opstiAktOKolektivnomZiguIliZiguGarancije, "OPSTI_AKT").pipe(
          mergeMap(response => {
            zahtevZaPriznanjeZiga.opstiAktOKolektivnomZiguIliZiguGarancije.naziv = response;
            return of(response);
          })
        )
      );
    }

    return forkJoin(uploadObservables).pipe(
      mergeMap(() => {
        console.log(zahtevZaPriznanjeZiga.toXML());
        return this.http.post(
          this.config.zigEndpoint,
          zahtevZaPriznanjeZiga.toXML(),
          {
            observe: "body",
            responseType: "text",
            headers: {
              "Content-Type": "application/xml",
              Accept: "application/xml"
            }
          }
        );
      })
    );
  }

  uploadFile(file: FileDTO, fileType: string) {
    const exampleFormData = new FormData();
    exampleFormData.append("file", file.dokument);
    return this.http.post(this.config.zigEndpoint + `uploadFile/${fileType}`, exampleFormData, {
      observe: "body",
      responseType: "text"
    });
  }

  putAcceptRequest() {
    return of(null);
  }

  putRejectRequest() {
    return of(null);
  }
}
