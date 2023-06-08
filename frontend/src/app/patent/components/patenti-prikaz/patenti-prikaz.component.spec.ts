import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentiPrikazComponent } from './patenti-prikaz.component';

describe('PatentiPrikazComponent', () => {
  let component: PatentiPrikazComponent;
  let fixture: ComponentFixture<PatentiPrikazComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentiPrikazComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentiPrikazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
