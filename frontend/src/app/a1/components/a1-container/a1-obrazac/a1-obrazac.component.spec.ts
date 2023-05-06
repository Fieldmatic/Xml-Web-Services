import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1ObrazacComponent } from './a1-obrazac.component';

describe('A1ObrazacComponent', () => {
  let component: A1ObrazacComponent;
  let fixture: ComponentFixture<A1ObrazacComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1ObrazacComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1ObrazacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
