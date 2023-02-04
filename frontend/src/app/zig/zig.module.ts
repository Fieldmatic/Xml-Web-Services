import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { ZigContainerComponent } from './components/zig-container/zig-container.component';
import { ZigRoutingModule } from './zig-routing.module';
import { ZigAllRequestsComponent } from './components/zig-container/zig-all-requests/zig-all-requests.component';
import { ZigRequestComponent } from './components/zig-container/zig-request/zig-request.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';

@NgModule({
  declarations: [
    ZigContainerComponent,
    ZigAllRequestsComponent,
    ZigRequestComponent,
  ],
  imports: [
    ZigRoutingModule,
    SharedModule,
    MatPaginatorModule,
    MatSlideToggleModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatSortModule,
    MatTableModule,
    MatMenuModule,
  ],
})
export class ZigModule {}
