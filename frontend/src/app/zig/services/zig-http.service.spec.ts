import { TestBed } from '@angular/core/testing';

import { ZigHttpService } from './zig-http.service';

describe('ZigHttpService', () => {
  let service: ZigHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZigHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
