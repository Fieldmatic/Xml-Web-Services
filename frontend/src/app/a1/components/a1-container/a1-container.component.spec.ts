import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1ContainerComponent } from './a1-container.component';

describe('A1ContainerComponent', () => {
  let component: A1ContainerComponent;
  let fixture: ComponentFixture<A1ContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1ContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1ContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
