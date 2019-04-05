import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../models';
import { environment } from '@env/environment';
import {HttpClient } from "@angular/common/http";

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User;

  constructor(private http: HttpClient) { }

  getUsersForAdmin(): Observable<User[]> {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  getUsers(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get(`${API_URL}/users`, {params: httpParams});
  }



  deleteUser(userId: Number) {
    return this.http.delete(`${API_URL}/users/${userId}`);
  }

  banUser(id: Number) {
    return this.http.put(`${API_URL}/users/${id}/ban`, this.user);
  }

  unbanUser(id: Number) {
    return this.http.put<User>(`${API_URL}/users/${id}/unban`, this.user);
  }
}
