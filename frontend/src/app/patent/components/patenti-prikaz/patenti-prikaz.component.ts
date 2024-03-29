import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {OsnovniPodaciObrascu} from "../../../shared/model/OsnovniPodaciObrascu";
import {PatentService} from "../../services/patent.service";
import {saveAs} from 'file-saver';
import {xml2json} from 'xml-js';
import {AuthService} from "../../../auth/services/auth.service";
import {LoggedInUser} from "../../../auth/model/logged-in-user";

@Component({
  selector: 'app-patenti-prikaz',
  templateUrl: './patenti-prikaz.component.html',
  styleUrls: ['./patenti-prikaz.component.scss']
})
export class PatentiPrikazComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;
  metadataElementi: string[] = ['brojPrijave', 'datumPodnosenja', 'imePodnosioca', 'prezimePodnosioca']
  loggedInUser: LoggedInUser = null;

  constructor(private patentService: PatentService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.loggedInUser.subscribe(user => {
      this.loggedInUser = user
      this.patentService.dobaviSveZahteve(this.loggedInUser.role).subscribe((result) => {
        this.prikaziRezultatePretrage(result);
      });
    });
  }

  izvrsiNaprednuPetragu(triplets: any) {
    this.patentService.pretraziZahtevePoMetapodacima(triplets, this.loggedInUser.role).subscribe((result) => {
      this.prikaziRezultatePretrage(result);
    });
  }

  izvrsiObicnuPretragu(filters: string[]) {
    this.patentService.pretraziZahtevePoTekstu(filters, this.loggedInUser.role).subscribe((result) => {
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
      saveAs(response, 'patent_' + id + '.json');
    });
  }

}
