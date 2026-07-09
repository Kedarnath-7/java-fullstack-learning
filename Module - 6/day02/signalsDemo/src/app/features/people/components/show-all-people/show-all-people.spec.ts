import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllPeople } from './show-all-people';

describe('ShowAllPeople', () => {
  let component: ShowAllPeople;
  let fixture: ComponentFixture<ShowAllPeople>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAllPeople],
    }).compileComponents();

    fixture = TestBed.createComponent(ShowAllPeople);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
