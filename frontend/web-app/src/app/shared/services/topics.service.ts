import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Topic} from "@shared/models/Topic";
import {environment} from "@env/environment";
import {PostDTO} from "@shared/models/dto/PostDTO";
import { TopicPaginationDto } from '../models/dto/TopicPaginationDto';

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private http: HttpClient) {
  }

  getTopicsByCategory(id: number, httpParams?: HttpParams | any):  Observable<any> {
    return this.http.get(`${API_URL}/topics/` + id + `/paging`, {params: httpParams});
  }

  getTopic(id: number) {
    return this.http.get(`${API_URL}/topics/` + id );
  }
  saveNewTopic(postDTO: PostDTO) {
    return this.http.post(`${API_URL}/topics/createTopic`, postDTO);
  }

  getTopics(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get(`${API_URL}/topics`, {params: httpParams});
  }

  getTopicWithPostLikes(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get(`${API_URL}/topics/mostLikes`, {params: httpParams});
  }
  pinTopic(topicId: number){
    return this.http.put<any>(`${API_URL}/topics/` + topicId + `/pin`, null);
  }

  getNewestPostByTopic(id: number): Observable<any>{
    return this.http.get(`${API_URL}/topics/` + id + `/newestPost`);
  }
}
