import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialForm } from './special-form';

describe('SpecialForm', () => {
  let component: SpecialForm;
  let fixture: ComponentFixture<SpecialForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpecialForm],
    }).compileComponents();

    fixture = TestBed.createComponent(SpecialForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
