import {PatentObrazacComponent} from './components/patent-obrazac/patent-obrazac.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PatentiPrikazComponent} from "./components/patenti-prikaz/patenti-prikaz.component";
import {PatentContainerComponent} from "./components/patent-container/patent-container.component";

const routes: Routes = [
  {
    path: 'patent',
    component: PatentContainerComponent,
    children: [
      {
        path: 'zahtevi',
        pathMatch: 'full',
        component: PatentiPrikazComponent,
      },
      {
        path: 'novi',
        pathMatch: 'full',
        component: PatentObrazacComponent,
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PatentRoutingModule {}
