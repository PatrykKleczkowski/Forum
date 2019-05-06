import { TopicsService } from '@shared/services/topics.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Topic, Category } from '@app/shared/models';
import { MatSort, MatPaginator } from '@angular/material';
import { merge, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { CategoryService } from '@app/shared/services';

@Component({
  selector: 'app-topic-display-rank-list',
  templateUrl: './topic-display-rank-list.component.html',
  styleUrls: ['./topic-display-rank-list.component.scss']
})
export class TopicDisplayRankListComponent implements AfterViewInit {

  topics: Topic[] = []
  resultsLength = 3;
  category: Category;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private topicService: TopicsService,private categoryService: CategoryService,
     private _router: Router) { }

  ngAfterViewInit() {
    this.handleTableChanges();
  }
  handleTableChanges = () => {
    merge(this.sort.sortChange)
      .pipe(
        startWith({}),
        switchMap(() => {
          const params = {
            sort: `${this.sort.active},${this.sort.direction}`,
            size: `5` + ''
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

  getPosts(topicId: number) {
    this.getCategoryByTopic(topicId);
    this._router.navigate([`/home/categories/`, this.category.id, `topics`, 2]);
  }

  getCategoryByTopic(id: number) {
    this.categoryService.getCategoryByTopic(id).subscribe((category:any) => {
      this.category=category;
    })
  }
}
