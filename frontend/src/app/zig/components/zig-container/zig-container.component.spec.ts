import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigContainerComponent } from './zig-container.component';

describe('ZigContainerComponent', () => {
  let component: ZigContainerComponent;
  let fixture: ComponentFixture<ZigContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
