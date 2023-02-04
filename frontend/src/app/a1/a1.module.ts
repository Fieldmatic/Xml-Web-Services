import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { A1RoutingModule } from './a1-routing.module';
import { A1ObrazacComponent } from './components/a1-obrazac/a1-obrazac.component';
import { DodajAutoraDijalogComponent } from './components/dodaj-autora-dijalog/dodaj-autora-dijalog.component';

@NgModule({
  declarations: [A1ObrazacComponent, DodajAutoraDijalogComponent],
  imports: [CommonModule, A1RoutingModule, SharedModule],
})
export class A1Module {}
