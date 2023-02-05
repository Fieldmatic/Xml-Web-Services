import { PatentObrazacComponent } from './components/patent-obrazac/patent-obrazac.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'patent',
    component: PatentObrazacComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PatentRoutingModule {}
