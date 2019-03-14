import { Injectable } from '@angular/core';
import { User } from '@app/shared/models';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  public user: User;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
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
