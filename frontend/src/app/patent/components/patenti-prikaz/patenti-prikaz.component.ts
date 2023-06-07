import {Component} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {OsnovniPodaciObrascu} from "../../../shared/model/OsnovniPodaciObrascu";
import {PatentService} from "../../services/patent.service";
import {saveAs} from 'file-saver';


declare var require: any;

@Component({
  selector: 'app-patenti-prikaz',
  templateUrl: './patenti-prikaz.component.html',
  styleUrls: ['./patenti-prikaz.component.scss']
})
export class PatentiPrikazComponent {
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;

  constructor(private patentService: PatentService) {
  }

  izvrsiNaprednuPetragu(triplets: any) {
    this.patentService.pretraziZahtevePoMetapodacima(triplets).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  izvrsiObicnuPretragu(filters: string[]) {
    this.patentService.pretraziZahtevePoTekstu(filters).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  prikaziRezultatePretrage(result: string) {
    let xmlResult = require('xml-js').xml2json(result, {
      compact: true,
      spaces: 4,
      trim: true,
    });
    let jsonResult = JSON.parse(xmlResult);
    if (jsonResult.zahteviDTO.zahtevi) {
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>(this.kreirajPatentZahteve(this.napraviListuOdPristiglihZahteva(jsonResult.zahteviDTO.zahtevi)));
    } else {
      this.dataSource = new MatTableDataSource<OsnovniPodaciObrascu>([]);
    }
  }

  kreirajPatentZahteve(listaXmlZahteva: any) {
    return listaXmlZahteva.map(xmlZahtev => this.patentService.kreirajOsnovniPatentOdXmlZahteva(xmlZahtev))
  }

  napraviListuOdPristiglihZahteva(zahtevi: any) {
    return Array.isArray(zahtevi) ? zahtevi : [zahtevi];
  }

  preuzmiPdf(id: string) {
    this.patentService.preuzmiPdf(id).subscribe(data => {
      saveAs(data, 'patent_' + id + '.pdf');
    });
  }

  preuzmiHtml(id: string) {
    this.patentService.preuzmiHtml(id).subscribe(data => {
      saveAs(data, 'patent_' + id + '.html');
    });
  }

  preuzmiRdfMetapodatke(id: string) {
    this.patentService.preuzmiRdfMetapodatke(id).subscribe((response: any) => {
      saveAs(response, 'patent_' + id + '.rdf')
    });
  }

  preuzmiJsonMetapodatke(id: string) {
    this.patentService.preuzmiJsonMetapodatke(id).subscribe((response: any) => {
      saveAs(response, 'patent_' + id + '.rdf');
    });
  }

}
