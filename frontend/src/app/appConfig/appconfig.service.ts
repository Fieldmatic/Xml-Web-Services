import { AppConfig } from './appconfig.interface';
import { InjectionToken } from '@angular/core';
import { environment } from 'src/environments/environment';

export const APP_SERVICE_CONFIG = new InjectionToken<AppConfig>('app.config');

export const APP_CONFIG: AppConfig = {
  usersEndpoint: environment.usersEndpoint,
  zigEndpoint: environment.zigEndpoint,
  patentEndpoint: environment.patentEndpoint,
  autorskoPravoEndpoint: environment.autorskoPravoEndpoint,
};
