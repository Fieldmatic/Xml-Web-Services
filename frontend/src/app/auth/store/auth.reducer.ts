import * as AuthActions from './auth.actions';
import { LoggedInUser } from '../model/logged-in-user';

export interface State {
  user: LoggedInUser;
  authError: string;
  loading: boolean;
  userExists: boolean;
}

const initialState = {
  user: null,
  authError: null,
  loading: false,
  userExists: null,
};

export function authReducer(
  state = initialState,
  action: AuthActions.AuthActions
) {
  switch (action.type) {
    case AuthActions.LOGIN_SUCCESS:
      return {
        ...state,
        authError: null,
        user: action.user,
        loading: false,
      };

    case AuthActions.LOGOUT_END:
      return {
        ...state,
        user: null,
      };
    case AuthActions.LOGIN_START:
      return {
        ...state,
        authError: null,
        loading: true,
      };
    case AuthActions.AUTHENTICATE_FAIL:
      return {
        ...state,
        user: null,
        authError: action.payload,
        loading: false,
      };
    default:
      return state;
  }
}
