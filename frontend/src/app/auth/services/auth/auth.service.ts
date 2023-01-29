import * as AuthActions from '../../store/auth.actions';
import { LoggedInUser } from 'src/app/auth/model/logged-in-user';
import { AppConfig } from '../../../appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from '../../../appConfig/appconfig.service';
import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponseData } from '../../model/login-response-data';
import { Store } from '@ngrx/store';
import * as fromApp from '../../../store/app.reducer';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenExpirationTimer = null;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient,
    private store: Store<fromApp.AppState>
  ) {}

  setLogoutTimer(expirationDuration: number) {
    this.tokenExpirationTimer = setTimeout(() => {
      this.store.dispatch(new AuthActions.LogoutStart());
    }, expirationDuration);
  }

  clearLogoutTimer() {
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
      this.tokenExpirationTimer = null;
    }
  }

  UserExists(email: string) {
    return this.http.get<Observable<boolean>>(
      this.config.usersEndpoint + `auth/user-exists/${email}`
    );
  }
}
