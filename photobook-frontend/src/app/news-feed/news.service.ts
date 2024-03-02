import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewsItem } from '../models/news-item.model';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private apiUrl = 'http://localhost:8080/v1/publicacao/listar-publicacoes';

  constructor(private http: HttpClient) { }

  getNews(): Observable<NewsItem[]> {
    return this.http.get<NewsItem[]>(this.apiUrl);
  }
}
