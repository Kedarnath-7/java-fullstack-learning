import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TrackFormComponent } from './components/track-form/track-form.component';
import { TrackListComponent } from './components/track-list/track-list.component';
import { TrackService } from './services/track.service';
import { Track, TrackRequest } from './models/track.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, NavbarComponent, TrackFormComponent, TrackListComponent],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  private readonly trackService = inject(TrackService);

  tracks = signal<Track[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  searchResult = signal<Track | null>(null);
  searchError = signal(false);

  ngOnInit(): void {
    this.loadTracks();
  }

  loadTracks(): void {
    this.loading.set(true);
    this.error.set(null);
    this.trackService.getAllTracks().subscribe({
      next: (tracks) => {
        this.tracks.set(tracks);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set('Failed to load tracks. Please try again.');
        this.loading.set(false);
        console.error('Error loading tracks:', err);
      },
    });
  }

  onTrackCreated(track: TrackRequest): void {
    this.trackService.createTrack(track).subscribe({
      next: (newTrack) => {
        this.tracks.update((tracks) => [...tracks, newTrack]);
      },
      error: (err) => {
        this.error.set('Failed to create track. Please try again.');
        console.error('Error creating track:', err);
      },
    });
  }

  onDeleteTrack(trackId: number): void {
    this.trackService.deleteTrack(trackId).subscribe({
      next: () => {
        this.tracks.update((tracks) => tracks.filter((t) => t.id !== trackId));
      },
      error: (err) => {
        this.error.set('Failed to delete track. Please try again.');
        console.error('Error deleting track:', err);
      },
    });
  }

  onSearchByTitle(title: string): void {
    this.searchResult.set(null);
    this.searchError.set(false);
    this.trackService.searchByTitle(title).subscribe({
      next: (track) => {
        this.searchResult.set(track);
        this.searchError.set(false);
      },
      error: () => {
        this.searchResult.set(null);
        this.searchError.set(true);
      },
    });
  }

  onClearSearch(): void {
    this.searchResult.set(null);
    this.searchError.set(false);
  }

  dismissError(): void {
    this.error.set(null);
  }
}
