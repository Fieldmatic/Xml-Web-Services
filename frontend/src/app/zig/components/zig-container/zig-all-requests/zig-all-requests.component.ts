import { Component, OnDestroy, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { ZigHttpService } from "../../../services/zig-http.service";
import { saveAs } from "file-saver";
import { xml2json } from "xml-js";
import { ZahtevZaPriznanjeZiga } from "../../../model/zahtev-za-priznanje-ziga.model";
import { OsnovniPodaciObrascu } from "../../../../shared/model/OsnovniPodaciObrascu";
import { AuthService } from "../../../../auth/services/auth.service";
import { LoggedInUser } from "../../../../auth/model/logged-in-user";
import { Subscription } from "rxjs";

@Component({
  selector: "app-zig-all-requests",
  templateUrl: "./zig-all-requests.component.html",
  styleUrls: ["./zig-all-requests.component.scss"]
})
export class ZigAllRequestsComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ["id", "name", "date", "status", "download"];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;
  user: LoggedInUser;
  subscription: Subscription;
  metadataElementi: string[] = [
    'brojPrijave',
    'datumPodnosenja',
    'imePodnosioca',
    'prezimePodnosioca',
    'vrstaZiga',
  ];

  constructor(private zigService: ZigHttpService, private authService: AuthService) {
  }

  ngOnInit() {
    this.subscription = this.authService.loggedInUser.subscribe(user => {
      this.user = user;
      this.zigService.getAllRequests(this.user.role).subscribe(response => this.prikaziRezultatePretrage(response));
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  preuzmiHtml(id) {
    this.zigService.getRequestXHtml(id).subscribe(data => {
      saveAs(data, "zahtev_za_priznanje_ziga_" + id + ".html");
    });
  }

  preuzmiPdf(id) {
    this.zigService.getRequestPdf(id).subscribe(data => {
      saveAs(data, "zahtev_za_priznanje_ziga_" + id + ".pdf");
    });
  }

  preuzmiRdfMetapodatke(id) {
    this.zigService.getRequestMetadataRdf(id).subscribe((response: any) => {
      saveAs(response, "zahtev_za_priznanje_ziga_" + id + ".rdf");
    });
  }

  preuzmiJsonMetapodatke(id) {
    this.zigService.getRequestMetadataJson(id).subscribe((response: any) => {
      saveAs(response, "zahtev_za_priznanje_ziga_" + id + ".json");
    });
  }

  izvrsiObicnuPretragu($event: any) {
    this.zigService.getAllByText($event, this.user.role).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  izvrsiNaprednuPetragu($event: any) {
    this.zigService.getAllByMetadata($event, this.user.role).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  prikaziRezultatePretrage(result: string) {
    const xmlResult = xml2json(result, {
      compact: true,
      spaces: 4,
      trim: true
    });
    const jsonResult = JSON.parse(xmlResult);
    if (jsonResult.zahteviDTO.zahtevi) {
      console.log(this.kreirajZ1Zahteve(this.napraviListuOdPristiglihZahteva(jsonResult.zahteviDTO.zahtevi)));
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>(this.kreirajZ1Zahteve(this.napraviListuOdPristiglihZahteva(jsonResult.zahteviDTO.zahtevi)));
    } else {
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>([]);
    }
  }

  kreirajZ1Zahteve(listaXmlZahteva: any) {
    return listaXmlZahteva.map(xmlZahtev => ZahtevZaPriznanjeZiga.parseXML(xmlZahtev));
  }

  napraviListuOdPristiglihZahteva(zahtevi: any) {
    return Array.isArray(zahtevi) ? zahtevi : [zahtevi];
  }
}
