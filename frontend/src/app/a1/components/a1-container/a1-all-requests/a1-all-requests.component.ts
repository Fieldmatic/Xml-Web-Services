import {Component} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {A1Service} from 'src/app/a1/services/a1.service';


declare var require: any;

interface OsnovniPodaciObrascu {
  id: string;
  nazivPodnosioca: string;
  datumPodnosenja: Date;
  status: string
}

@Component({
  selector: 'app-a1-all-requests',
  templateUrl: './a1-all-requests.component.html',
  styleUrls: ['./a1-all-requests.component.scss'],
})
export class A1AllRequestsComponent {
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<OsnovniPodaciObrascu>;

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
    let xmlResult = require('xml-js').xml2json(result, {
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
}
