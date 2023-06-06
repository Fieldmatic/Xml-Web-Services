import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1ObradaObrascaComponent } from './a1-obrada-obrasca.component';

describe('A1ObradaObrascaComponent', () => {
  let component: A1ObradaObrascaComponent;
  let fixture: ComponentFixture<A1ObradaObrascaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1ObradaObrascaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1ObradaObrascaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
