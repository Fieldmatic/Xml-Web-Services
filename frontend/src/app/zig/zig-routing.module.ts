import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ZigContainerComponent } from './components/zig-container/zig-container.component';
import { ZigAllRequestsComponent } from './components/zig-container/zig-all-requests/zig-all-requests.component';
import { ZigRequestComponent } from './components/zig-container/zig-request/zig-request.component';

const routes: Routes = [
  {
    path: 'zig',
    component: ZigContainerComponent,
    children: [
      {
        path: 'zahtevi',
        pathMatch: 'full',
        component: ZigAllRequestsComponent,
      },
      {
        path: ':id',
        component: ZigRequestComponent,
      },
      {
        path: 'novi',
        pathMatch: 'full',
        component: ZigRequestComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ZigRoutingModule {}
