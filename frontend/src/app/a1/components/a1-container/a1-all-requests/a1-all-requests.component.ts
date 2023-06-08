import {Component} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {A1Service} from 'src/app/a1/services/a1.service';
import {saveAs} from 'file-saver';
import {OsnovniPodaciObrascu} from "../../../../shared/model/OsnovniPodaciObrascu";
import { xml2json } from 'xml-js';


@Component({
  selector: 'app-a1-all-requests',
  templateUrl: './a1-all-requests.component.html',
  styleUrls: ['./a1-all-requests.component.scss'],
})
export class A1AllRequestsComponent {
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;
  metadataElementi: string[] = ['brojPrijave', 'datumPodnosenja', 'imePodnosioca', 'prezimePodnosioca']

  constructor(private a1Service: A1Service) {
  }

  izvrsiNaprednuPetragu(triplets: any) {
    this.a1Service.pretraziZahtevePoMetapodacima(triplets).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  izvrsiObicnuPretragu(filters: string[]) {
    console.log(filters)
    this.a1Service.pretraziZahtevePoTekstu(filters).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  prikaziRezultatePretrage(result: string) {
    let xmlResult = xml2json(result, {
      compact: true,
      spaces: 4,
      trim: true,
    });
    let jsonResult = JSON.parse(xmlResult);
    if (jsonResult.zahteviDTO.zahtevi) {
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>(this.kreirajA1Zahteve(this.napraviListuOdPristiglihZahteva(jsonResult.zahteviDTO.zahtevi)));
    } else {
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>([]);
    }
  }

  kreirajA1Zahteve(listaXmlZahteva: any) {
    return listaXmlZahteva.map(xmlZahtev => this.a1Service.kreirajA1ZahtevOdXmlZahteva(xmlZahtev))
  }

  napraviListuOdPristiglihZahteva(zahtevi: any) {
    return Array.isArray(zahtevi) ? zahtevi : [zahtevi];
  }

  preuzmiPdf(id: string) {
    this.a1Service.preuzmiPdf(id).subscribe(data => {
      saveAs(data, 'zahtev_za_autorska_prava_' + id + '.pdf');
    });
  }

  preuzmiHtml(id: string) {
    this.a1Service.preuzmiHtml(id).subscribe(data => {
      saveAs(data, 'zahtev_za_autorska_prava_' + id + '.html');
    });
  }

  preuzmiRdfMetapodatke(id: string) {
    this.a1Service.preuzmiRdfMetapodatke(id).subscribe((response: any) => {
      saveAs(response, 'autorska_prava_' + id + '.rdf')
    });
  }

  preuzmiJsonMetapodatke(id: string) {
    this.a1Service.preuzmiJsonMetapodatke(id).subscribe((response: any) => {
      saveAs(response, 'autorska_prava_' + id + '.json');
    });
  }
}
