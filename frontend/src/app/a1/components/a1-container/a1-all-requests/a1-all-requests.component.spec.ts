import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1AllRequestsComponent } from './a1-all-requests.component';

describe('A1AllRequestsComponent', () => {
  let component: A1AllRequestsComponent;
  let fixture: ComponentFixture<A1AllRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1AllRequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1AllRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
