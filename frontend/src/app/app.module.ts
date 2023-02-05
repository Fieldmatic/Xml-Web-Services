import { A1Module } from './a1/a1.module';
import { DirectivesModule } from './directives/directives.module';
import { AuthEffects } from './auth/store/auth.effects';
import { APP_CONFIG, APP_SERVICE_CONFIG } from './appConfig/appconfig.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { StoreRouterConnectingModule } from '@ngrx/router-store';
import { ReactiveFormsModule } from '@angular/forms';
import * as fromApp from './store/app.reducer';
import { SharedModule } from './shared/shared.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthModule } from 'src/app/auth/auth.module';
import { ToastrModule } from 'ngx-toastr';
import { HomepageComponent } from './homepage/homepage.component';
import { PatentModule } from './patent/patent-module';
@NgModule({
  declarations: [AppComponent, NavbarComponent, HomepageComponent],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,
    StoreModule.forRoot(fromApp.appReducer),
    EffectsModule.forRoot([AuthEffects]),
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
    StoreRouterConnectingModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AuthModule,
    AppRoutingModule,
    DirectivesModule,
    A1Module,
    PatentModule
  ],
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
