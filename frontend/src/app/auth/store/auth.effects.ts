import { AuthService } from 'src/app/auth/services/auth/auth.service';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, ofType, createEffect } from '@ngrx/effects';
import { tap } from 'rxjs';
import { catchError, map, of, switchMap } from 'rxjs';
import { AppConfig } from 'src/app/appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/appConfig/appconfig.service';
import { LoggedInUser } from '../model/logged-in-user';
import { LoginResponseData } from '../model/login-response-data';
import { Observable } from 'rxjs';
import * as AuthActions from './auth.actions';

const handleError = (errorRes: any) => {
  console.log(errorRes.error);
  if (errorRes.hasOwnProperty('error')) {
    return of(new AuthActions.AuthenticateFail(errorRes.error.message));
  }
  return of(new AuthActions.AuthenticateFail('An unknown error occurred'));
};

const handleAuthentication = (
  token: string,
  expiresIn: number,
  role?: string,
  email?: string
) => {
  const expirationDate = new Date(new Date().getTime() + expiresIn);
  const user = new LoggedInUser(email, role, token, expirationDate);
  localStorage.setItem('userData', JSON.stringify(user));
  return new AuthActions.LoginSuccess(user);
};

@Injectable()
export class AuthEffects {
  authLogin = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.LOGIN_START),
      switchMap((authData: AuthActions.LoginStart) => {
        return this.http
          .post<LoginResponseData>(this.config.apiEndpoint + 'auth/login', {
            email: authData.payload.email,
            password: authData.payload.password,
          })
          .pipe(
            map((resData) => {
              this.authService.setLogoutTimer(resData.expiresIn);
              return handleAuthentication(
                resData.token,
                resData.expiresIn,
                resData.role,
                authData.payload.email
              );
            }),
            catchError((errorResp) => {
              return handleError(errorResp);
            })
          );
      })
    )
  );

  loginRedirect = createEffect(
    () =>
      this.actions$.pipe(
        ofType(AuthActions.LOGIN_SUCCESS),
        tap(() => {
          this.router.navigate(['/']);
        })
      ),
    { dispatch: false }
  );

  signupSuccess = createEffect(
    () =>
      this.actions$.pipe(
        ofType(AuthActions.SIGNUP_SUCCESS),
        tap(() => {
          this.router.navigate(['/auth/login']);
        })
      ),
    { dispatch: false }
  );

  authSingup = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.SIGNUP_START),
      switchMap((signUpAction: AuthActions.SignupStart) => {
        return this.http
          .post<{
            email: string;
            name: string;
            surname: string;
          }>(this.config.apiEndpoint + 'passenger/create', {
            email: signUpAction.payload.email,
            password: signUpAction.payload.password,
            name: signUpAction.payload.firstName,
            surname: signUpAction.payload.lastName,
            city: signUpAction.payload.city,
            phoneNumber: signUpAction.payload.phoneNumber,
            profilePicture: signUpAction.payload.profilePicture,
          })
          .pipe(
            map(() => {
              return new AuthActions.SignupSuccess();
            }),
            catchError((errorResp) => {
              return handleError(errorResp);
            })
          );
      })
    )
  );

  authLogoutEnd = createEffect(
    () =>
      this.actions$.pipe(
        ofType(AuthActions.LOGOUT_END),
        tap(() => {
          localStorage.removeItem('userData');
          this.authService.clearLogoutTimer();
          this.router.navigate(['/']);
        })
      ),
    { dispatch: false }
  );

  autoLogin = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.AUTO_LOGIN),
      map(() => {
        const userData = JSON.parse(localStorage.getItem('userData'));
        if (!userData) {
          return { type: 'DUMMY' };
        }

        let expirationDate = new Date(userData._tokenExpirationDate);
        const loadedUser = new LoggedInUser(
          userData.email,
          userData.role,
          userData._token,
          expirationDate
        );
        if (loadedUser.token) {
          this.authService.setLogoutTimer(
            new Date(userData._tokenExpirationDate).getTime() -
              new Date().getTime()
          );
          return new AuthActions.LoginSuccess(loadedUser);
        }
        return { type: 'DUMMY' };
      })
    )
  );

  constructor(
    private actions$: Actions,
    private http: HttpClient,
    private router: Router,
    private authService: AuthService,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {}
}
