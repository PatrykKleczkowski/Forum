import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "@env/environment";
import { User } from '../models';
const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }
  getPostsByTopic(id: Number): Observable<any> {
    return this.http.get(`${API_URL}/topics/` + id + `/withUsers`);
  }

}
