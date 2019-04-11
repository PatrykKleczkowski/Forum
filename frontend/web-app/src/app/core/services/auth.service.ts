import { UserCredentials } from '@app/shared/models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { AccountInfo, Role } from '@app/shared/models/account-info';
import { environment } from '@env/environment';


const API_URL = environment.apiUrl;
export const AUTHORIZATION_HEADER = 'Authorization';
const AUTHORIZATION_KEY = 'authorization';
const USERNAME_KEY = 'username';
const ROLE_KEY = 'role';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(userCredentials: UserCredentials): Observable<any> {
    return this.http
      .post<AccountInfo>(`${API_URL}/login`, userCredentials, { observe: 'response' })
      .pipe(
        tap((response: HttpResponse<AccountInfo>) => {
          const token = response.headers.get(AUTHORIZATION_HEADER);
          this.storeAuthorization(token);

          const body = response.body;
          this.storeAccountInfo(body);
        })
      );
  }

  getAuthorization(): string {
    return localStorage.getItem(AUTHORIZATION_KEY);
  }

  private storeAuthorization(authToken: string) {
    localStorage.setItem(AUTHORIZATION_KEY, authToken);
  }

  private storeAccountInfo(accountInfo: AccountInfo) {
    localStorage.setItem(USERNAME_KEY, accountInfo.username);
    localStorage.setItem(ROLE_KEY, accountInfo.role.toString());
  }

  logout() {
    localStorage.removeItem(AUTHORIZATION_KEY);
    localStorage.removeItem(USERNAME_KEY);
    localStorage.removeItem(ROLE_KEY);
         window.location.reload();
  }

  isLogged() {
    return localStorage.getItem(AUTHORIZATION_KEY) !== null;
  }

  isAdmin(): boolean {
    return this.hasRole(Role.ADMIN);
  }

  isUser(): boolean {
    return this.hasRole(Role.USER);
  }


  private hasRole(role: Role): boolean {
    return localStorage.getItem('role') === role.toString();
  }

  // private isAuthor(authorId: number): boolean {
  //   return localStorage.getItem('id') === authorId.toString();
  // }

  register(userCredentials: UserCredentials): Observable<any> {
    const url = `${API_URL}/users/signup`;

    return this.http
      .post(url, userCredentials)
      .pipe(
        tap(response => {
          this.login(userCredentials);
        })
      );
  }
}
