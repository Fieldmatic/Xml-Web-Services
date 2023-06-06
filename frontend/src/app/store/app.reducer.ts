import { ActionReducerMap } from '@ngrx/store';
import * as fromZig from '../zig/store/zig.reducer';

export interface AppState {
  zig: fromZig.State;
}

export const appReducer: ActionReducerMap<AppState> = {
  zig: fromZig.zigReducer,
};
