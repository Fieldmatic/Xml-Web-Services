import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigAllRequestsComponent } from './zig-all-requests.component';

describe('ZigAllRequestsComponent', () => {
  let component: ZigAllRequestsComponent;
  let fixture: ComponentFixture<ZigAllRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigAllRequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigAllRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
