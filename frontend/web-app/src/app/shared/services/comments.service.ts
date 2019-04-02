import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "@env/environment";
import {Observable} from "rxjs";

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) {
  }

  // getPostsByTopic(id: Number): Observable<any> {
  //   return this.http.get(`${API_URL}/topics/` + id + `/withUsers`);
  // }
  //
  // saveNewPost(postDTO: PostDTO) {
  //   return this.http.post(`${API_URL}/createPost`, postDTO);
  // }

  getCommentsByPost(id: Number): Observable<any> {
    return this.http.get(`${API_URL}/posts/` + id + `/comments`)
  }


}
