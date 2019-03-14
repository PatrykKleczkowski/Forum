import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Topic} from "@shared/models/Topic";
import {environment} from "@env/environment";

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private http: HttpClient) {
  }


  getTopicsByCategory(id: Number): Observable<any> {
    return this.http.get(`${API_URL}/categories/` + id + `/topics`);
  }
}