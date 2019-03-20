import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Topic} from "@shared/models/Topic";
import {environment} from "@env/environment";
import {PostDTO} from "@shared/models/dto/PostDTO";

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

  saveNewTopic(postDTO: PostDTO) {
    return this.http.post(`${API_URL}/createTopic`, postDTO);
    console.log(postDTO);
  }

  getTopics(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get('/api/topics', {params: httpParams});
  }

  getTopicWithPostLikes(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get('/api/topics/mostLikes', {params: httpParams});
  }
}
