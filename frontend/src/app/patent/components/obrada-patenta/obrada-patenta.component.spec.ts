import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObradaPatentaComponent } from './obrada-patenta.component';

describe('ObradaPatentaComponent', () => {
  let component: ObradaPatentaComponent;
  let fixture: ComponentFixture<ObradaPatentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObradaPatentaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObradaPatentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
