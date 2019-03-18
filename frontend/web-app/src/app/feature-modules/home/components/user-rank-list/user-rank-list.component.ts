import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { UserService } from '@app/shared/services/user.service';
import { User } from '@app/shared/models/user';
import {MatPaginator, MatSort} from '@angular/material';
import { merge, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';
@Component({
  selector: 'app-user-rank-list',
  templateUrl: './user-rank-list.component.html',
  styleUrls: ['./user-rank-list.component.scss']
})
export class UserRankListComponent implements AfterViewInit {

  users: User[] = [];
  resultsLength = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private userService: UserService) { }


  ngAfterViewInit () {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.paginator.pageSize = 5;
    this.handleTableChanges();
  }

  handleTableChanges = () => {
    merge(this.sort.sortChange, this.paginator.page, this.paginator.pageSize)
      .pipe(
        startWith({}),
        switchMap(() => {
          const params = {
            sort: `${this.sort.active},${this.sort.direction}`,
            page: this.paginator.pageIndex + '',
            size: this.paginator.pageSize + ''
          };

          return this.userService.getUsers(params);
        }),
        map((data: any) => {
          this.resultsLength = data.page.totalElements;

          return data._embedded.users;
        }),
        catchError(() => {
          return of([]);
        })
      )
      .subscribe(data => this.users = data);
  }
}
