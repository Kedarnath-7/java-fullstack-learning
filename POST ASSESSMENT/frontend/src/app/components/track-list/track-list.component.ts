import { Component, EventEmitter, Input, Output, computed } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Track } from '../../models/track.model';

@Component({
  selector: 'app-track-list',
  standalone: true,
  imports: [CommonModule, DatePipe, FormsModule],
  template: `
    <div class="card shadow-sm">
      <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
        <h5 class="mb-0">
          <i class="bi bi-music-note-list me-2"></i>Track Library
        </h5>
        <span class="badge bg-light text-success">
          {{ isSearchMode ? '1 result' : tracks.length + ' tracks' }}
        </span>
      </div>
      <div class="card-body">
        <!-- Search Bar -->
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="Search by title..."
            [(ngModel)]="searchTitle"
            (keyup.enter)="onSearch()"
          />
          <button class="btn btn-outline-primary" type="button" (click)="onSearch()">
            <i class="bi bi-search"></i> Search
          </button>
          @if (isSearchMode) {
            <button class="btn btn-outline-secondary" type="button" (click)="onClearSearch()">
              <i class="bi bi-x-lg"></i> Show All
            </button>
          }
        </div>

        <!-- Search Mode Indicator -->
        @if (isSearchMode && searchResult) {
          <div class="alert alert-info d-flex justify-content-between align-items-center" role="alert">
            <span>
              <i class="bi bi-funnel me-2"></i>
              Showing search results for "<strong>{{ searchTitle }}</strong>"
            </span>
          </div>
        }

        @if (searchError) {
          <div class="alert alert-warning" role="alert">
            <i class="bi bi-exclamation-triangle me-2"></i>
            <strong>Not Found:</strong> No track found with title "{{ searchTitle }}"
            <button class="btn btn-sm btn-outline-warning ms-3" (click)="onClearSearch()">
              Show All Tracks
            </button>
          </div>
        }

        <!-- Track Table -->
        @if (displayTracks.length === 0 && !searchError) {
          <div class="text-center py-5">
            <i class="bi bi-music-note-beamed display-4 text-muted"></i>
            <p class="text-muted mt-3">No tracks available. Add your first track!</p>
          </div>
        } @else if (displayTracks.length > 0) {
          <div class="table-responsive">
            <table class="table table-hover align-middle">
              <thead class="table-light">
                <tr>
                  <th>ID</th>
                  <th>Title</th>
                  <th>Album</th>
                  <th>Release Date</th>
                  <th>Play Count</th>
                  <th class="text-center">Actions</th>
                </tr>
              </thead>
              <tbody>
                @for (track of displayTracks; track track.id) {
                  <tr [class.table-info]="isSearchMode">
                    <td>
                      <span class="badge bg-secondary">{{ track.id }}</span>
                    </td>
                    <td>
                      <strong>{{ track.title }}</strong>
                    </td>
                    <td>{{ track.albumName }}</td>
                    <td>{{ track.releaseDate | date: 'mediumDate' }}</td>
                    <td>
                      <span class="badge bg-info text-dark">
                        <i class="bi bi-play-fill"></i> {{ track.playCount | number }}
                      </span>
                    </td>
                    <td class="text-center">
                      <button
                        class="btn btn-sm btn-outline-danger"
                        (click)="onDelete(track.id!)"
                        title="Delete track"
                      >
                        <i class="bi bi-trash"></i> Delete
                      </button>
                    </td>
                  </tr>
                }
              </tbody>
            </table>
          </div>
        }
      </div>
    </div>
  `,
})
export class TrackListComponent {
  @Input() tracks: Track[] = [];
  @Input() searchResult: Track | null = null;
  @Input() searchError: boolean = false;
  @Output() deleteTrack = new EventEmitter<number>();
  @Output() searchByTitle = new EventEmitter<string>();
  @Output() clearSearch = new EventEmitter<void>();

  searchTitle = '';

  get isSearchMode(): boolean {
    return this.searchResult !== null;
  }

  get displayTracks(): Track[] {
    if (this.searchResult) {
      return [this.searchResult];
    }
    return this.tracks;
  }

  onDelete(trackId: number): void {
    if (confirm('Are you sure you want to delete this track?')) {
      this.deleteTrack.emit(trackId);
    }
  }

  onSearch(): void {
    if (this.searchTitle.trim()) {
      this.searchByTitle.emit(this.searchTitle.trim());
    }
  }

  onClearSearch(): void {
    this.searchTitle = '';
    this.clearSearch.emit();
  }
}
