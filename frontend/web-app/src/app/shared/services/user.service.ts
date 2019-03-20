import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '@app/shared/models/user';
import { HttpClient } from '../../../../node_modules/@angular/common/http';

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
    return this.http.get<User[]>('/api/users');
  }

  deleteUser(userId: Number) {
    return this.http.delete(`/api/users/${userId}`);
  }

  banUser(id: Number) {
    return this.http.put(`/api/users/${id}/ban`, this.user);
  }

  unbanUser(id: Number) {
    return this.http.put(`/api/users/${id}/unban`, this.user);

  }
}
