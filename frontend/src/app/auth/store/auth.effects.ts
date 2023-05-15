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
import { ToastrService } from 'ngx-toastr';
import * as AuthActions from './auth.actions';
import * as xml2js from 'xml2js';

const handleError = (errorRes: any, toastrService: ToastrService) => {
  xml2js.parseString(errorRes.error, (error, result) => {
    toastrService.error(
      result['exceptionResponse']['message'][0],
      'Notification',
      {
        timeOut: 3000,
        tapToDismiss: false,
        newestOnTop: true,
        positionClass: 'toast-top-center',
      }
    );
  });
};

const notifySuccess = (message: string, toastrService: ToastrService) => {
  toastrService.success(message, 'Notification', {
    timeOut: 3000,
    tapToDismiss: false,
    newestOnTop: true,
    positionClass: 'toast-top-center',
  });
};

const handleAuthentication = (loginResponse, toastrService: ToastrService) => {
  let user: LoggedInUser = xml2js.parseString(
    loginResponse,
    (error, result) => {
      const user = new LoggedInUser(
        result['loginResponse']['email'][0],
        result['loginResponse']['role'][0],
        result['loginResponse']['ime'][0],
        result['loginResponse']['prezime'][0],
      );
      localStorage.setItem('userData', JSON.stringify(user));
      notifySuccess('Uspesno ste se ulogovali!', toastrService);
      return user;
    }
  );
  return user;
};

@Injectable()
export class AuthEffects {
  authLogin = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.LOGIN_START),
      switchMap((authData: AuthActions.LoginStart) => {
        let body =
          '<loginRequest>' +
          '<email>' +
          authData.payload.email +
          '</email>' +
          '<password>' +
          authData.payload.password +
          '</password>' +
          '</loginRequest>';
        return this.http
          .post(this.config.usersEndpoint + 'auth/login', body, {
            observe: 'body',
            responseType: 'text',
            headers: {
              'Content-Type': 'application/xml',
              Accept: 'application/xml',
            },
          })
          .pipe(
            map((resData) => {
              return new AuthActions.LoginSuccess(
                handleAuthentication(resData, this.toastrService)
              );
            }),
            catchError((errorResp) => {
              handleError(errorResp, this.toastrService);
              return of();
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
          this.router.navigate(['/patent']);
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

  authSignup = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.SIGNUP_START),
      switchMap((signUpAction: AuthActions.SignupStart) => {
        let body =
          '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>' +
          '<user xmlns="http://www.xml.tim14.rs/user">' +
          '<email>' +
          signUpAction.payload.email +
          '</email>' +
          '<password>' +
          signUpAction.payload.password +
          '</password>' +
          '<ime>' +
          signUpAction.payload.name +
          '</ime>' +
          '<prezime>' +
          signUpAction.payload.surname +
          '</prezime>' +
          '<role>' +
          signUpAction.payload.role +
          '</role>' +
          '</user>';
        return this.http
          .post(this.config.usersEndpoint + 'auth', body, {
            observe: 'body',
            responseType: 'text',
            headers: {
              'Content-Type': 'application/xml',
              Accept: 'application/xml',
            },
          })
          .pipe(
            map(() => {
              notifySuccess('Uspesno ste se registrovali!', this.toastrService);
              return new AuthActions.SignupSuccess();
            }),
            catchError((errorResp) => {
              handleError(errorResp, this.toastrService);
              return of();
            })
          );
      })
    )
  );

  authLogout = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.LOGOUT_START),
      map(() => {
        const userData = JSON.parse(localStorage.getItem('userData'));
        return new AuthActions.LogoutEnd();
      })
    )
  );

  authLogoutEnd = createEffect(
    () =>
      this.actions$.pipe(
        ofType(AuthActions.LOGOUT_END),
        tap(() => {
          localStorage.removeItem('userData');
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
          this.router.navigate(['/a1/novi']);
          return { type: 'DUMMY' };
        }
        const loadedUser = new LoggedInUser(userData.email, userData.role, userData.name, userData.surname);
        return new AuthActions.LoginSuccess(loadedUser);
      })
    )
  );

  constructor(
    private actions$: Actions,
    private http: HttpClient,
    private router: Router,
    private authService: AuthService,
    private toastrService: ToastrService,
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig
  ) {}
}
