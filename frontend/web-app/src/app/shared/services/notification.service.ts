import { map, catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {NotificationResponse} from "@shared/models/NotificationResponse";
import {environment} from "@env/environment";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  getUserNotifications(): Observable<NotificationResponse[]> {
    return this.http.get<NotificationResponse[]>(`${API_URL}/notifications` )
      .pipe(
        map(
          (response: any) => {
            return response.content;
          }
        ),
        catchError((error: any) => {
          return of([]);
        }),
      );
  }

  setAsDisplayed(notificationId: number): Observable<number> {
    return this.http.get<number>(`${API_URL}/notifications/${notificationId}/setAsDisplayed`);
  }
}
