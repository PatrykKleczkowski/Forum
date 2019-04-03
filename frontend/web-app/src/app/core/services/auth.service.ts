import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import {
  UserCredentials,
  AccountInfo,
  Role
} from '@shared/models';

export const AUTHORIZATION_HEADER = 'Authorization';
const AUTHORIZATION_KEY = 'authorization';
const USERNAME_KEY = 'username';
const ROLE_KEY = 'role';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {
  }

  login(userCredentials: UserCredentials): Observable<any> {
    return this.http
      .post<AccountInfo>('/api/login', userCredentials, { observe: 'response' })
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
    this.router.navigateByUrl('/home');
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


  register(userCredentials: UserCredentials): Observable<any> {

    return this.http
      .post('api/users/signup', userCredentials)
      .pipe(
        tap(response => {
          this.login(userCredentials);
        })
      );
  }
}
