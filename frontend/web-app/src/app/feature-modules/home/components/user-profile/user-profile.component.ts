import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '@app/shared/services';
import { User } from '@app/shared/models/user';
import { UserProfileDto } from '@app/shared/models/dto/userProfileDto';
import { AuthService } from '@app/core/services';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  username: string;
  user: User;
  userProfileDto: UserProfileDto;
  constructor(private activatedRouter: ActivatedRoute, private userService: UserService,
    private authService: AuthService) { }

  ngOnInit() {
    this.activatedRouter.params.subscribe(params => {
      this.username = params['username'];
    });
    this.getUser(this.username);
  }

  getUser(username: string) {
    this.userService.getUserProfile(username).subscribe((user: any) => {
      this.userProfileDto = user;
console.log(this.userProfileDto.user.username);

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
