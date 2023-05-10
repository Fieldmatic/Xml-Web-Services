import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaZahtevaComponent } from './pretraga-zahteva.component';

describe('PretragaZahtevaComponent', () => {
  let component: PretragaZahtevaComponent;
  let fixture: ComponentFixture<PretragaZahtevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaZahtevaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaZahtevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
