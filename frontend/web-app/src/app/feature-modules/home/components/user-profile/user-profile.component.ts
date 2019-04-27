import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '@app/shared/services';
import { User } from '@app/shared/models/user';
import { UserProfileDto } from '@app/shared/models/dto/userProfileDto';
import { AuthService } from '@app/core/services';
import {MatSort, MatTableDataSource} from '@angular/material';
import { Topic } from '@app/shared/models/Topic';
import { Post } from '@app/shared/models/Post';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  username: string;
  user: User;
  posts: Post[] =[];
  topics: Topic[];
  constructor(private activatedRouter: ActivatedRoute, private userService: UserService,
    private authService: AuthService) { }

  ngOnInit() {
    this.activatedRouter.params.subscribe(params => {
      this.username = params['username'];
    });
    this.getUser(this.username);
    this.getUserPosts(this.username);
    this.getUserTopics(this.username);
  }

  getUser(username: string) {
    this.userService.getUserProfile(username).subscribe((user: User) => {
      this.user = user;
    });
  }

  getUserTopics(username: string) {
    this.userService.getUserTopicsProfile(username).subscribe((topics: any) => {
      this.topics = topics.content;
    });
  }

  getUserPosts(username: string) {
    this.userService.getUserPostsProfile(username).subscribe((posts: any) => {
      this.posts = posts.content;
    });
  }
  isLogged() {
    return this.authService.isLogged();
  }

  isAdmin() {
    return this.authService.isAdmin();
  }

  banAccount(user: User) {
    this.userService.banUser(user.id)
      .subscribe((resp: any) => {
        this.getUser(user.username);
      });
  }

  unbanAccount(user: User) {
    this.userService.unbanUser(user.id)
      .subscribe((resp: any) => {
        this.getUser(user.username);
      });
  }
}
