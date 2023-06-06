import { A1ContainerComponent } from './components/a1-container/a1-container.component';
import { A1ObrazacComponent } from './components/a1-container/a1-obrazac/a1-obrazac.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { A1AllRequestsComponent } from './components/a1-container/a1-all-requests/a1-all-requests.component';
import {A1ObradaObrascaComponent} from "./components/a1-container/a1-obrada-obrasca/a1-obrada-obrasca.component";

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
      {
        path: 'novi',
        pathMatch: 'full',
        component: A1ObrazacComponent,
      },
      { path: 'obrada/:id',
        component: A1ObradaObrascaComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class A1RoutingModule {}
