import { SharedModule } from '../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatentObrazacComponent } from './components/patent-obrazac/patent-obrazac.component';
import { PatentRoutingModule } from './patent-routing.module';
import { DodajPrijavuDijalogComponent } from './components/dodaj-prijavu-dijalog/dodaj-prijavu-dijalog.component';

@NgModule({
  declarations: [PatentObrazacComponent, DodajPrijavuDijalogComponent],
  imports: [CommonModule, PatentRoutingModule, SharedModule],
})
export class PatentModule {}
