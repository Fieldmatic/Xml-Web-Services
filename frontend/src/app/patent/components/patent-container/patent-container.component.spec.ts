import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentContainerComponent } from './patent-container.component';

describe('PatentContainerComponent', () => {
  let component: PatentContainerComponent;
  let fixture: ComponentFixture<PatentContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
