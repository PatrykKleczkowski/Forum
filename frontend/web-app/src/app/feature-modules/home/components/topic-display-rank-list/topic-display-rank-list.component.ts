import {TopicsService} from '@shared/services/topics.service';
import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {Category, Topic} from '@app/shared/models';
import {MatPaginator, MatSort} from '@angular/material';
import {merge, of} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {CategoryService} from '@app/shared/services';

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
    this.categoryService.getCategoryByTopic(topicId).subscribe((category: any) => {
      this.category=category;
      this._router.navigate([`/home/categories`, this.category.id, `topics`, topicId]);
    })

  }

}
