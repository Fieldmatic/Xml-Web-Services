import { Component } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { OsnovniPodaciObrascu } from "../../../../a1/components/a1-container/a1-all-requests/a1-all-requests.component";
import { ZigHttpService } from "../../../services/zig-http.service";
import { saveAs } from "file-saver";
import { xml2json } from "xml-js";
import { ZahtevZaPriznanjeZiga } from "../../../model/zahtev-za-priznanje-ziga.model";

@Component({
  selector: "app-zig-all-requests",
  templateUrl: "./zig-all-requests.component.html",
  styleUrls: ["./zig-all-requests.component.scss"]
})
export class ZigAllRequestsComponent {
  displayedColumns: string[] = ["id", "name", "date", "status", "download"];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;

  constructor(private zigService: ZigHttpService) {
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
    this.zigService.getAllByText($event).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  izvrsiNaprednuPetragu($event: any) {
    this.zigService.getAllByMetadata($event).subscribe((result) => {
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
