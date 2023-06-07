import {A1Module} from './a1/a1.module';
import {DirectivesModule} from './directives/directives.module';
import {APP_CONFIG, APP_SERVICE_CONFIG} from './appConfig/appconfig.service';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from './shared/shared.module';
import {HttpClientModule} from '@angular/common/http';
import {AuthModule} from 'src/app/auth/auth.module';
import {ToastrModule} from 'ngx-toastr';
import {HomepageComponent} from './homepage/homepage.component';
import {PatentModule} from './patent/patent-module';
import {ZigModule} from './zig/zig.module';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { PatentiPrikazComponent } from './patent/components/patenti-prikaz/patenti-prikaz.component';
import { PatentContainerComponent } from './patent/components/patent-container/patent-container.component';
import { ObradaPatentaComponent } from './patent/components/obrada-patenta/obrada-patenta.component';

@NgModule({
  declarations: [AppComponent, NavbarComponent, HomepageComponent, PatentiPrikazComponent, PatentContainerComponent, ObradaPatentaComponent],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    SharedModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AuthModule,
    ZigModule,
    AppRoutingModule,
    DirectivesModule,
    A1Module,
    PatentModule
  ],
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG,
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
