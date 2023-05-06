import { Action } from '@ngrx/store';
import { ZahtevZaPriznanjeZiga } from '../model/zahtev-za-priznanje-ziga.model';
import { PrijavaResponse } from '../model/prijavaResponse.model';

export const GET_ALL_REQUESTS = '[Zig] Get all requests';
export const SET_REQUESTS = '[Zig] Set requests';
export const GET_REQUEST = '[Zig] Get request';
export const SET_REQUEST = '[Zig] Set request';
export const GET_ALL_BY_METADATA = '[Zig] Get all requests by metadata';
export const GET_REFERENCED_DOCUMENT = '[Zig] Get referenced document';
export const GET_REQUEST_PDF = '[Zig] Get request as PDF';
export const GET_REQUEST_XHTML = '[Zig] Get request as XHTML';
export const GET_REQUEST_METADATA_RDF = '[Zig] Get request metadata as RDF';
export const GET_REQUEST_METADATA_JSON = '[Zig] Get request as JSON';
export const CREATE_REQUEST = '[Zig] Create request';
export const CREATE_REQUEST_SUCCESS = '[Zig] Create request successful';
export const ACCEPT_REQUEST = '[Zig] Accept request';
export const REJECT_REQUEST = '[Zig] Reject request';
export const REQUEST_STATUS_CHANGE_SUCCESS =
  '[Zig] Request status change successful';
export const GET_REPORT = '[Zig] Get report';
export const REQUEST_FAILED = '[Zig] Request failed';
export const DOWNLOAD_DOCUMENT = '[Zig] Download document';

export class GetAllRequests implements Action {
  readonly type = GET_ALL_REQUESTS;
}

export class SetAllRequests implements Action {
  readonly type = SET_REQUESTS;

  constructor(public payload: PrijavaResponse[]) {}
}

export class GetRequest implements Action {
  readonly type = GET_REQUEST;

  constructor(public payload: string) {}
}

export class SetRequest implements Action {
  readonly type = SET_REQUEST;

  // TODO: define request structure
  constructor(public payload: any) {}
}

export class GetAllByMetadata implements Action {
  readonly type = GET_ALL_BY_METADATA;

  // TODO: define metadata structure
  constructor(public payload: any) {}
}

export class GetReferencedDocument implements Action {
  readonly type = GET_REFERENCED_DOCUMENT;

  constructor(public payload: string) {}
}

export class GetRequestPdf implements Action {
  readonly type = GET_REQUEST_PDF;

  constructor(public payload: string) {}
}

export class GetRequestXHtml implements Action {
  readonly type = GET_REQUEST_XHTML;

  constructor(public payload: string) {}
}

export class GetRequestMetadataRdf implements Action {
  readonly type = GET_REQUEST_METADATA_RDF;

  constructor(public payload: string) {}
}

export class GetRequestMetadataJson implements Action {
  readonly type = GET_REQUEST_METADATA_JSON;

  constructor(public payload: string) {}
}

export class CreateRequest implements Action {
  readonly type = CREATE_REQUEST;

  constructor(public payload: ZahtevZaPriznanjeZiga) {}
}

export class CreateRequestSuccess implements Action {
  readonly type = CREATE_REQUEST_SUCCESS;

  constructor(public payload: string) {}
}

export class AcceptRequest implements Action {
  readonly type = ACCEPT_REQUEST;

  constructor(public payload: string) {}
}

export class RejectRequest implements Action {
  readonly type = REJECT_REQUEST;

  constructor(public payload: { requestId: string; explanation: string }) {}
}

export class RequestStatusChangeSuccess implements Action {
  readonly type = REQUEST_STATUS_CHANGE_SUCCESS;

  constructor(public payload: string) {}
}

export class GetReport implements Action {
  readonly type = GET_REPORT;

  constructor(public payload: { initDate: Date; termDate: Date }) {}
}

export class RequestFailed implements Action {
  readonly type = REQUEST_FAILED;

  constructor(public payload: any) {}
}

export class DownloadDocument implements Action {
  readonly type = DOWNLOAD_DOCUMENT;

  constructor(public payload: { blob: Blob; extension: string; id: string }) {}
}

export type ZigActions =
  | GetAllRequests
  | SetAllRequests
  | GetRequest
  | SetRequest
  | GetAllByMetadata
  | GetReferencedDocument
  | GetRequestPdf
  | GetRequestXHtml
  | GetRequestMetadataRdf
  | GetRequestMetadataJson
  | CreateRequest
  | AcceptRequest
  | RejectRequest
  | GetReport
  | RequestFailed
  | DownloadDocument;
