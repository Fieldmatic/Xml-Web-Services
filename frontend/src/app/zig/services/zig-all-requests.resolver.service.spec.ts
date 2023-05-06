import { TestBed } from '@angular/core/testing';

import { ZigAllRequestsResolverService } from './zig-all-requests.resolver.service';

describe('ZigAllRequestsResolverService', () => {
  let service: ZigAllRequestsResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZigAllRequestsResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
