import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-zig-all-requests',
  templateUrl: './zig-all-requests.component.html',
  styleUrls: ['./zig-all-requests.component.scss'],
})
export class ZigAllRequestsComponent implements AfterViewInit, OnInit {
  searchControl: FormControl;
  metadataSearch: FormGroup;
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<{
    brojZahteva: string;
    podnosilac: { ime: string; prezime: string };
    datumPodnosenja: Date;
    status: string;
  }>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.searchControl = new FormControl('');
    this.metadataSearch = new FormGroup({
      metadata: new FormControl('', Validators.required),
    });
    let testZahtevi = [
      {
        brojZahteva: 'Z 1/545',
        podnosilac: { ime: 'ime', prezime: 'string' },
        datumPodnosenja: new Date(),
        status: 'odbijen',
      },
      {
        brojZahteva: 'Z 1/545',
        podnosilac: { ime: 'ime', prezime: 'string' },
        datumPodnosenja: new Date(),
        status: 'odbijen',
      },
      {
        brojZahteva: 'Z 1/545',
        podnosilac: { ime: 'ime', prezime: 'string' },
        datumPodnosenja: new Date(),
        status: 'odbijen',
      },
    ];
    this.dataSource = new MatTableDataSource<{
      brojZahteva: string;
      podnosilac: { ime: string; prezime: string };
      datumPodnosenja: Date;
      status: string;
    }>(testZahtevi);
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  searchMetadata() {
    console.log(this.metadataSearch.value.metadata);
  }
}
