import {Component, OnInit} from '@angular/core';
import {NotificationResponse} from "@shared/models/NotificationResponse";
import {DialogService} from "@shared/services";
import {ToastrService} from "ngx-toastr";
import {NotificationService} from "@shared/services/notification.service";
import {Router} from "@angular/router";
import {NavbarComponent} from "@features/home/components";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  arrNotifications: NotificationResponse[] = [];


  constructor(private dialogService: DialogService,
              private toastr: ToastrService,
              private notificationService: NotificationService,
              private _router: Router,
              public navbarComponnent: NavbarComponent) {
  }

  ngOnInit() {
    this.fetchArrNotifications();
  }

  fetchArrNotifications(): void {
    this.notificationService.getUserNotifications()
      .subscribe(response => {
        this.arrNotifications = response;
        this.navbarComponnent.handleNotificationReaded(this.arrNotifications.length);

      });

  }

  setAsDisplayed(notificationId: number) {
    this.notificationService.setAsDisplayed(notificationId)
      .subscribe(response => {
        this.arrNotifications = this.arrNotifications.filter(elem => elem.id = notificationId);
      });
    this.fetchArrNotifications();
  }

  goToUserProfile(username: string) {
    this._router.navigate([`/home/profile`, username]);
  }

  getPosts(categoryId: number, topicId: number, notificationId: number) {
    this.setAsDisplayed(notificationId);
    this._router.navigate([`/home/categories`, categoryId, `topics`, topicId]);
  }


}
