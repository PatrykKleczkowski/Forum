import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "@env/environment";
import {NewCommentDTO} from "@shared/models/dto/NewCommentDTO";

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) {
  }

  saveNewComment(newCommentDTO: NewCommentDTO) {
    return this.http.post(`${API_URL}/comments/createComment`, newCommentDTO);
  }


}
