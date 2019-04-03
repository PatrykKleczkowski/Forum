import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '@app/shared/models/user';
import { HttpClient } from '../../../../node_modules/@angular/common/http';
import { environment } from '@env/environment';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})

export class UserService {

  public user: User;

  constructor(private http: HttpClient) { }


  getUsers(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get('/api/users', {params: httpParams});
  }

  getUsersForAdmin(): Observable<User[]> {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  deleteUser(userId: Number) {
    return this.http.delete(`/api/users/${userId}`);
  }

  banUser(id: Number) {
    return this.http.put(`/api/users/${id}/ban`, null);
  }

  unbanUser(id: Number) {
    return this.http.put<User>(`${API_URL}/users/${id}/unban`, null);

  }
}
