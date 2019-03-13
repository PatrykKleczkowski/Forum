import { Component, OnInit } from '@angular/core';
import { UserService } from '@app/shared/services/user.service';
import { User } from '@app/shared/models';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'active', 'actions'];
  users: User[];
  constructor(private userService: UserService) { }

  ngOnInit() {
  this.getUsers();
  }

  private getUsers = () => {
    this.userService.getUsers().subscribe((users: any) => {
      this.users = users._embedded.users;
    });
  }

  banAccount(user: User) {
    this.userService.banUser(user.id)
    .subscribe((resp: any) => {
      this.getUsers();
    });
  }

  deleteAccount(userId: Number) {
    this.userService.deleteUser(userId)
    .subscribe((resp: any) => {
      this.getUsers();
    });
  }
}
