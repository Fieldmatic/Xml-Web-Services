import { A1ObrazacComponent } from './components/a1-obrazac/a1-obrazac.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'a1',
    component: A1ObrazacComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class A1RoutingModule {}
