import {SharedModule} from '../shared/shared.module';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {A1RoutingModule} from './a1-routing.module';
import {A1ObrazacComponent} from './components/a1-container/a1-obrazac/a1-obrazac.component';
import {DodajAutoraDijalogComponent} from './components/a1-container/a1-obrazac/dodaj-autora-dijalog/dodaj-autora-dijalog.component';
import {A1AllRequestsComponent} from './components/a1-container/a1-all-requests/a1-all-requests.component';
import {A1ContainerComponent} from './components/a1-container/a1-container.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { A1ObradaObrascaComponent } from './components/a1-container/a1-obrada-obrasca/a1-obrada-obrasca.component';


@NgModule({
  declarations: [
    A1ObrazacComponent,
    DodajAutoraDijalogComponent,
    A1AllRequestsComponent,
    A1ContainerComponent,
    A1ObradaObrascaComponent,
  ],
  imports: [CommonModule, A1RoutingModule, SharedModule, MatPaginatorModule],
})
export class A1Module {}
