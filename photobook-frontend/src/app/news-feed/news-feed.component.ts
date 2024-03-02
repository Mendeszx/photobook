import { Component, OnInit } from '@angular/core';
import { NewsService } from '../services/news.service';
import { NewsItem } from '../models/news-item.model';

@Component({
  selector: 'app-news-feed',
  templateUrl: './news-feed.component.html',
  styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit {
  newsItems: NewsItem[] = [];

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
    this.loadNewsFeed();
  }

  loadNewsFeed(): void {
    this.newsService.getNews().subscribe(
      (data: NewsItem[]) => {
        this.newsItems = data;
      },
      (error) => {
        console.error('Error fetching news feed:', error);
      }
    );
  }
}
