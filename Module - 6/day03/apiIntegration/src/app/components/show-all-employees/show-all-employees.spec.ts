import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllEmployees } from './show-all-employees';

describe('ShowAllEmployees', () => {
  let component: ShowAllEmployees;
  let fixture: ComponentFixture<ShowAllEmployees>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAllEmployees],
    }).compileComponents();

    fixture = TestBed.createComponent(ShowAllEmployees);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
