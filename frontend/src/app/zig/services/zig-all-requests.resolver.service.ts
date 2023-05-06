import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { PrijavaResponse } from '../model/prijavaResponse.model';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import * as fromApp from '../../store/app.reducer';
import * as ZigActions from '../store/zig.actions';
import { Actions, ofType } from '@ngrx/effects';

@Injectable({
  providedIn: 'root',
})
export class ZigAllRequestsResolverService
  implements Resolve<PrijavaResponse[]>
{
  constructor(
    private store: Store<fromApp.AppState>,
    private actions$: Actions
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<PrijavaResponse[]>
    | Promise<PrijavaResponse[]>
    | PrijavaResponse[] {
    return this.store.select('zig').pipe(
      take(1),
      map((zigState) => {
        return zigState.requests;
      }),
      switchMap((requests) => {
        if (requests.length === 0) {
          this.store.dispatch(new ZigActions.GetAllRequests());
          return this.actions$.pipe(ofType(ZigActions.SET_REQUESTS), take(1));
        } else {
          return of(requests);
        }
      })
    );
  }
}
