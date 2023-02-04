import { TestBed } from '@angular/core/testing';

import { A1Service } from './a1.service';

describe('A1Service', () => {
  let service: A1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(A1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
