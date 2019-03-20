import { TopicWithPostLikes } from './../../../../shared/models/dto/topicWithPostLikes';
import { TopicsService } from '@shared/services/topics.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatSort, MatPaginator } from '@angular/material';
import { merge, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-topic-with-most-likes',
  templateUrl: './topic-with-most-likes.component.html',
  styleUrls: ['./topic-with-most-likes.component.scss']
})
export class TopicWithMostLikesComponent implements AfterViewInit {

  topicsWithMostLikes: TopicWithPostLikes[] = [];
  resultsLength = 0;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(private topicService: TopicsService) { }

  ngAfterViewInit() {
    this.handleTableChanges();
  }
  handleTableChanges = () => {
    merge(this.sort.sortChange)
      .pipe(
        startWith({}),
        switchMap(() => {
          const params = {
            sort: `${this.sort.active},${this.sort.direction}`
          };

          return this.topicService.getTopicWithPostLikes(params)
        }),
        map((data: any) => {
          this.resultsLength = data.page.totalElements;

          return data._embedded.topicsWithMostLikes;
        }),
        catchError(() => {
          return of([]);
        })
      )
      .subscribe(data => this.topicsWithMostLikes = data);
  }
}
