import { ActionReducerMap } from '@ngrx/store';
import * as fromAuth from '../auth/store/auth.reducer';
import * as fromZig from '../zig/store/zig.reducer';

export interface AppState {
  auth: fromAuth.State;
  zig: fromZig.State;
}

export const appReducer: ActionReducerMap<AppState> = {
  auth: fromAuth.authReducer,
  zig: fromZig.zigReducer,
};
