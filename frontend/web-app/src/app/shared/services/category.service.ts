import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "@env/environment";

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }


  getAllCategories(): Observable<any>{
    return this.http.get(`${API_URL}/categories`);
  }






}
