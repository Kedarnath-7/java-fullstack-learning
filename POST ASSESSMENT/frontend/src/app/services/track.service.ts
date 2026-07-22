import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track, TrackRequest } from '../models/track.model';

@Injectable({
  providedIn: 'root',
})
export class TrackService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/music/platform/v1/tracks';

  getAllTracks(): Observable<Track[]> {
    return this.http.get<Track[]>(this.apiUrl);
  }

  createTrack(track: TrackRequest): Observable<Track> {
    return this.http.post<Track>(this.apiUrl, track);
  }

  deleteTrack(trackId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${trackId}`);
  }

  searchByTitle(title: string): Observable<Track> {
    const params = new HttpParams().set('title', title);
    return this.http.get<Track>(`${this.apiUrl}/search`, { params });
  }
}
