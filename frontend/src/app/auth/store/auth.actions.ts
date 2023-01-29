import { Action } from '@ngrx/store';
import { LoggedInUser } from '../model/logged-in-user';

export const LOGIN_START = '[Auth] Login Start';
export const LOGIN_SUCCESS = '[Auth] Login Success';
export const AUTHENTICATE_FAIL = '[Auth] Authenticate Fail';
export const LOGOUT_END = '[Auth] Logout End';
export const LOGOUT_START = '[Auth] Logout Start';
export const AUTO_LOGIN = '[Auth] Auto Login';
export const SIGNUP_START = '[Auth] Singup Start';
export const SIGNUP_SUCCESS = '[Auth] Singup Success';
export const CHANGE_USER_EXISTS_STATE = '[Auth] Change User exists';
export const USER_EXISTS_BY_EMAIL = '[Auth] User exists by email';

export class LoginSuccess implements Action {
  readonly type = LOGIN_SUCCESS;

  constructor(public user: LoggedInUser) {}
}

export class LogoutStart implements Action {
  readonly type = LOGOUT_START;
}

export class LogoutEnd implements Action {
  readonly type = LOGOUT_END;
}

export class LoginStart implements Action {
  readonly type = LOGIN_START;

  constructor(public payload: { email: string; password: string }) {}
}

export class AuthenticateFail implements Action {
  readonly type = AUTHENTICATE_FAIL;

  constructor(public payload: string) {}
}

export class SignupStart implements Action {
  readonly type = SIGNUP_START;

  constructor(
    public payload: {
      email: string;
      password: string;
      firstName: string;
      lastName: string;
      city: string;
      phoneNumber: string;
      profilePicture: string;
    }
  ) {}
}

export class SignupSuccess implements Action {
  readonly type = SIGNUP_SUCCESS;
}

export class AutoLogin implements Action {
  readonly type = AUTO_LOGIN;
}

export class UserExistsByEmail implements Action {
  readonly type = USER_EXISTS_BY_EMAIL;
  constructor(public payload: { email: string }) {}
}

export type AuthActions =
  | LoginSuccess
  | LogoutEnd
  | LoginStart
  | AuthenticateFail
  | SignupStart
  | SignupSuccess
  | AutoLogin
  | UserExistsByEmail;
