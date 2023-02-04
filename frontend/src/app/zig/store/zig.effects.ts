import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, of, switchMap, tap } from 'rxjs';

import { ZigHttpService } from '../services/zig-http.service';
import * as ZigActions from './zig.actions';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

// TODO: add data mapping
@Injectable()
export class ZigEffects {
  getAllRequests = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_ALL_REQUESTS),
      switchMap(() => {
        return this.zigHttpService.getAllRequests().pipe(
          map((result) => {
            return new ZigActions.SetAllRequests(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequest = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REQUEST),
      switchMap(() => {
        return this.zigHttpService.getRequest().pipe(
          map((result) => {
            return new ZigActions.SetRequest(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequestAllByMetadata = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_ALL_BY_METADATA),
      switchMap(() => {
        return this.zigHttpService.getAllByMetadata().pipe(
          map((result) => {
            return new ZigActions.SetAllRequests(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  // TODO: add document download
  getReferencedDocument = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REFERENCED_DOCUMENT),
      switchMap(() => {
        return this.zigHttpService.getReferencedDocument().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequestPdf = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REQUEST_PDF),
      switchMap(() => {
        return this.zigHttpService.getRequestPdf().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequestXHtml = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REQUEST_XHTML),
      switchMap(() => {
        return this.zigHttpService.getRequestXHtml().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequestMetadataRdf = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REQUEST_METADATA_RDF),
      switchMap(() => {
        return this.zigHttpService.getRequestMetadataRdf().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  getRequestMetadataJson = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REQUEST_METADATA_JSON),
      switchMap(() => {
        return this.zigHttpService.getRequestMetadataJson().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  createRequest = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.CREATE_REQUEST),
      switchMap(() => {
        return this.zigHttpService.postRequest().pipe(
          map((result) => {
            return new ZigActions.CreateRequestSuccess(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  successfulCreateRequestRedirection = createEffect(
    () =>
      this.actions$.pipe(
        ofType(ZigActions.CREATE_REQUEST_SUCCESS),
        tap((action: ZigActions.CreateRequestSuccess) => {
          this.router.navigate(['/zig/' + action.payload]);
        })
      ),
    { dispatch: false }
  );

  acceptRequest = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.ACCEPT_REQUEST),
      switchMap(() => {
        return this.zigHttpService.putAcceptRequest().pipe(
          map((result) => {
            const message = `Uspešno ste odobrili zahtev broj ${result}.`;
            return new ZigActions.RequestStatusChangeSuccess(message);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  rejectRequest = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.REJECT_REQUEST),
      switchMap(() => {
        return this.zigHttpService.putRejectRequest().pipe(
          map((result) => {
            const message = `Uspešno ste odbili zahtev broj ${result}.`;
            return new ZigActions.RequestStatusChangeSuccess(message);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  notifySuccess = createEffect(
    () =>
      this.actions$.pipe(
        ofType(ZigActions.REQUEST_STATUS_CHANGE_SUCCESS),
        tap((action: ZigActions.RequestStatusChangeSuccess) => {
          this.toastrService.success(action.payload);
        })
      ),
    { dispatch: false }
  );

  getReport = createEffect(() => {
    return this.actions$.pipe(
      ofType(ZigActions.GET_REPORT),
      switchMap(() => {
        return this.zigHttpService.getReport().pipe(
          map((result) => {
            return new ZigActions.DownloadDocument(result);
          }),
          catchError((err) => {
            return of(new ZigActions.RequestFailed(err));
          })
        );
      })
    );
  });

  notifyError = createEffect(
    () =>
      this.actions$.pipe(
        ofType(ZigActions.REQUEST_FAILED),
        tap((action: ZigActions.RequestFailed) => {
          this.toastrService.error(action.payload.error);
        })
      ),
    { dispatch: false }
  );

  downloadDocument = createEffect(
    () =>
      this.actions$.pipe(
        ofType(ZigActions.DOWNLOAD_DOCUMENT),
        tap((action: ZigActions.DownloadDocument) => {
          const url = window.URL.createObjectURL(action.payload);
          window.open(url);
        })
      ),
    { dispatch: false }
  );

  constructor(
    private toastrService: ToastrService,
    private actions$: Actions,
    private zigHttpService: ZigHttpService,
    private router: Router
  ) {}
}
