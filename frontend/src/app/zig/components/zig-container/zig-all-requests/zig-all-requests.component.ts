import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Store } from '@ngrx/store';
import * as fromApp from '../../../../store/app.reducer';
import * as ZigActions from '../../../store/zig.actions';
import { PrijavaResponse } from '../../../model/prijavaResponse.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-zig-all-requests',
  templateUrl: './zig-all-requests.component.html',
  styleUrls: ['./zig-all-requests.component.scss'],
})
export class ZigAllRequestsComponent
  implements AfterViewInit, OnInit, OnDestroy
{
  searchControl: FormControl;
  metadataSearch: FormGroup;
  displayedColumns: string[] = ['id', 'name', 'date', 'status', 'download'];
  dataSource: MatTableDataSource<PrijavaResponse>;
  storeSubscription: Subscription;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private store: Store<fromApp.AppState>) {}

  ngOnInit(): void {
    this.searchControl = new FormControl('');
    this.metadataSearch = new FormGroup({
      metadata: new FormControl('', Validators.required),
    });
    this.storeSubscription = this.store.select('zig').subscribe((zigState) => {
      this.dataSource = new MatTableDataSource<PrijavaResponse>(
        zigState.requests
      );
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  searchMetadata() {
    console.log(this.metadataSearch.value.metadata);
  }

  downloadXHTML(brojZahteva: string) {
    this.store.dispatch(new ZigActions.GetRequestXHtml(brojZahteva));
  }

  downloadPDF(brojZahteva: string) {
    this.store.dispatch(new ZigActions.GetRequestPdf(brojZahteva));
  }
}
