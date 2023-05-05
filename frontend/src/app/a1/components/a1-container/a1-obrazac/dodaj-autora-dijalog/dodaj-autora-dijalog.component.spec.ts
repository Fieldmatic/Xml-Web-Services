import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajAutoraDijalogComponent } from './dodaj-autora-dijalog.component';

describe('DodajAutoraDijalogComponent', () => {
  let component: DodajAutoraDijalogComponent;
  let fixture: ComponentFixture<DodajAutoraDijalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DodajAutoraDijalogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DodajAutoraDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
