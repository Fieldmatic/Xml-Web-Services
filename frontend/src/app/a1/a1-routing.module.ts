import { A1ContainerComponent } from './components/a1-container/a1-container.component';
import { A1ObrazacComponent } from './components/a1-container/a1-obrazac/a1-obrazac.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { A1AllRequestsComponent } from './components/a1-container/a1-all-requests/a1-all-requests.component';

const routes: Routes = [
  {
    path: 'a1',
    component: A1ContainerComponent,
    children: [
      {
        path: 'zahtevi',
        pathMatch: 'full',
        component: A1AllRequestsComponent,
      },
      // {
      //   path: ':id',
      //   component: ZigRequestComponent,
      // },
      {
        path: 'novi',
        pathMatch: 'full',
        component: A1ObrazacComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class A1RoutingModule {}
