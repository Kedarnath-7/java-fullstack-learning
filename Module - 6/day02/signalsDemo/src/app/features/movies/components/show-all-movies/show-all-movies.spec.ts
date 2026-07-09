import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllMovies } from './show-all-movies';

describe('ShowAllMovies', () => {
  let component: ShowAllMovies;
  let fixture: ComponentFixture<ShowAllMovies>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAllMovies],
    }).compileComponents();

    fixture = TestBed.createComponent(ShowAllMovies);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
