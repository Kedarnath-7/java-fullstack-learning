import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NormalForm } from './normal-form';

describe('NormalForm', () => {
  let component: NormalForm;
  let fixture: ComponentFixture<NormalForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NormalForm],
    }).compileComponents();

    fixture = TestBed.createComponent(NormalForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
