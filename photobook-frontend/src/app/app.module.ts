import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NewsFeedComponent } from './news-feed/news-feed.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NewsService } from './services/news.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NewsFeedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    NewsService ,
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
