import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ZigContainerComponent } from "./components/zig-container/zig-container.component";
import { ZigAllRequestsComponent } from "./components/zig-container/zig-all-requests/zig-all-requests.component";
import { ZigRequestComponent } from "./components/zig-container/zig-request/zig-request.component";
import {
  ZigRequestProcessingComponent
} from "./components/zig-container/zig-request-processing/zig-request-processing.component";

const routes: Routes = [
  {
    path: "z1",
    component: ZigContainerComponent,
    children: [
      {
        path: "zahtevi",
        pathMatch: "full",
        component: ZigAllRequestsComponent
      },
      {
        path: "novi",
        pathMatch: "full",
        component: ZigRequestComponent
      },
      {
        path: ":id",
        component: ZigRequestProcessingComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ZigRoutingModule {
}
