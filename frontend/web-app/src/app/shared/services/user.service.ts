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
}
