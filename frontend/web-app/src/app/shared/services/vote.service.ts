import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { Post } from '../models/Post';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})

export class VoteService {

  constructor(private http: HttpClient) { }

  public post: Post;

  voteUp(id: number){
    return this.http.post(`${API_URL}/posts/${id}/voteUp`, this.post);
  }

  voteDown(id: number){
    return this.http.post(`${API_URL}/posts/${id}/voteDown`, this.post);
  }
}
