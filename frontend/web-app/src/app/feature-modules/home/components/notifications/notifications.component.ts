import {Component, OnInit} from '@angular/core';
import {NotificationResponse} from "@shared/models/NotificationResponse";
import {DialogService} from "@shared/services";
import {ToastrService} from "ngx-toastr";
import {NotificationService} from "@shared/services/notification.service";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  arrNotifications: NotificationResponse[] = [];


  constructor(private dialogService: DialogService,
              private toastr: ToastrService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.fetchArrNotifications();
  }

  fetchArrNotifications(): void {
    this.notificationService.getUserNotifications()
      .subscribe(response => {
        this.arrNotifications = response;
      });

  }

  setAsDisplayed(notificationId: number) {
    this.notificationService.setAsDisplayed(notificationId)
      .subscribe(response => {
        this.arrNotifications = this.arrNotifications.filter(elem => elem.id = notificationId);
      });
    this.fetchArrNotifications();
  }

}
