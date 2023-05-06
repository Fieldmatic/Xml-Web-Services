import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigRequestComponent } from './zig-request.component';

describe('ZigRequestComponent', () => {
  let component: ZigRequestComponent;
  let fixture: ComponentFixture<ZigRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigRequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
