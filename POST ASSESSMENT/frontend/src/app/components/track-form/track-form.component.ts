import { Component, EventEmitter, Output, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TrackRequest } from '../../models/track.model';

@Component({
  selector: 'app-track-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="card shadow-sm">
      <div class="card-header bg-primary text-white">
        <h5 class="mb-0">
          <i class="bi bi-plus-circle me-2"></i>Add New Track
        </h5>
      </div>
      <div class="card-body">
        <form [formGroup]="trackForm" (ngSubmit)="onSubmit()">
          <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input
              type="text"
              class="form-control"
              id="title"
              formControlName="title"
              placeholder="Enter track title"
              [class.is-invalid]="isFieldInvalid('title')"
            />
            @if (isFieldInvalid('title')) {
              <div class="invalid-feedback">Title is required.</div>
            }
          </div>

          <div class="mb-3">
            <label for="albumName" class="form-label">Album Name</label>
            <input
              type="text"
              class="form-control"
              id="albumName"
              formControlName="albumName"
              placeholder="Enter album name"
              [class.is-invalid]="isFieldInvalid('albumName')"
            />
            @if (isFieldInvalid('albumName')) {
              <div class="invalid-feedback">Album name is required.</div>
            }
          </div>

          <div class="mb-3">
            <label for="releaseDate" class="form-label">Release Date</label>
            <input
              type="date"
              class="form-control"
              id="releaseDate"
              formControlName="releaseDate"
              [class.is-invalid]="isFieldInvalid('releaseDate')"
            />
            @if (isFieldInvalid('releaseDate')) {
              <div class="invalid-feedback">Release date is required.</div>
            }
          </div>

          <div class="mb-3">
            <label for="playCount" class="form-label">Play Count</label>
            <input
              type="number"
              class="form-control"
              id="playCount"
              formControlName="playCount"
              placeholder="Enter play count"
              min="0"
              [class.is-invalid]="isFieldInvalid('playCount')"
            />
            @if (isFieldInvalid('playCount')) {
              <div class="invalid-feedback">
                Play count is required and must be 0 or greater.
              </div>
            }
          </div>

          <button
            type="submit"
            class="btn btn-primary w-100"
            [disabled]="trackForm.invalid"
          >
            <i class="bi bi-plus-lg me-2"></i>Add Track
          </button>
        </form>
      </div>
    </div>
  `,
})
export class TrackFormComponent {
  @Output() trackCreated = new EventEmitter<TrackRequest>();

  private readonly fb = inject(FormBuilder);

  trackForm: FormGroup = this.fb.group({
    title: ['', Validators.required],
    albumName: ['', Validators.required],
    releaseDate: ['', Validators.required],
    playCount: [0, [Validators.required, Validators.min(0)]],
  });

  isFieldInvalid(fieldName: string): boolean {
    const field = this.trackForm.get(fieldName);
    return field ? field.invalid && (field.dirty || field.touched) : false;
  }

  onSubmit(): void {
    if (this.trackForm.valid) {
      const formValue = this.trackForm.value;
      const track: TrackRequest = {
        title: formValue.title,
        albumName: formValue.albumName,
        releaseDate: new Date(formValue.releaseDate),
        playCount: formValue.playCount,
      };
      this.trackCreated.emit(track);
      this.trackForm.reset({ playCount: 0 });
    }
  }
}
