import { TopicsService } from '@shared/services/topics.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Topic } from '@app/shared/models';
import { MatSort, MatPaginator } from '@angular/material';
import { merge, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-topic-newest-rank-list',
  templateUrl: './topic-newest-rank-list.component.html',
  styleUrls: ['./topic-newest-rank-list.component.scss']
})
export class TopicNewestRankListComponent implements AfterViewInit {

  topics: Topic[] = []
  resultsLength = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
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

          return this.topicService.getTopics(params)
        }),
        map((data: any) => {
          this.resultsLength = data.page.totalElements;

          return data._embedded.topics;
        }),
        catchError(() => {
          return of([]);
        })
      )
      .subscribe(data => this.topics = data);
  }
}
