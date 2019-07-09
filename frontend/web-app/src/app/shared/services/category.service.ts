import {Category} from '@shared/models/Category';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '@env/environment';

const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }


  // getAllCategories(): Observable<Category[]> {
  //   return this.http.get<Category[]>(`${API_URL}/categories`);
  // }

  getNewestPostDate(categoryId: number): Observable<any> {
    return this.http.get(`${API_URL}/categories/` + categoryId + `/newestPost`);
  }


  getProgrammingCategories(categoryType: string): Observable<Category[]> {
    const params = new HttpParams()
      .set('categoryType', categoryType);
    return this.http.get<Category[]>(`${API_URL}/categories`, {params});
  }

  getCategoryByTopic(id: number): Observable<any> {
    return this.http.get(`${API_URL}/topics/` + id + `/category`);
  }



}
