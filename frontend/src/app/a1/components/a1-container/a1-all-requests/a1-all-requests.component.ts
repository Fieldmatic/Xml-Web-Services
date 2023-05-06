import { Component, AfterViewInit, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { A1Service } from 'src/app/a1/services/a1.service';

declare var require: any;

@Component({
  selector: 'app-a1-all-requests',
  templateUrl: './a1-all-requests.component.html',
  styleUrls: ['./a1-all-requests.component.scss'],
})
export class A1AllRequestsComponent implements AfterViewInit, OnInit {
  searchControl: FormControl;
  metadataSearch: FormGroup;
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<{
    id: string;
    nazivPodnosioca: string;
    datumPodnosenja: Date;
    status: string
  }>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private a1Service: A1Service) {}

  ngOnInit(): void {
    this.searchControl = new FormControl('');
    this.metadataSearch = new FormGroup({
      metadata: new FormControl('', Validators.required),
    });
    this.ucitajSveA1Zahteve();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ucitajSveA1Zahteve() {
    this.a1Service.getAllZahtevi().subscribe((result) => {
      var xmlResult = require('xml-js').xml2json(result, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      var jsonResult = JSON.parse(xmlResult);
      console.log(jsonResult)
      this.dataSource = new MatTableDataSource<{
        id: string;
        nazivPodnosioca: string;
        datumPodnosenja: Date;
        status: string
      }>(this.kreirajA1Zahteve(jsonResult.zahteviDTO.zahtevi));
    });
  }

  kreirajA1Zahteve(listaXmlZahteva: any) {
    return listaXmlZahteva.map(xmlZahtev => this.a1Service.kreirajA1ZahtevOdXmlZahteva(xmlZahtev))
  }

  searchMetadata() {
    console.log(this.metadataSearch.value.metadata);
  }
}
