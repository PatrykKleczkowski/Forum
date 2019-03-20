import { RegisterDialogComponent } from '@features/home/components/register-dialog/register-dialog.component';
import {Injectable} from '@angular/core';
import {MatDialog} from '@angular/material';

import {NewTopicDialogComponent} from '@features/home/components/new-topic-dialog/new-topic-dialog.component';
import { LoginDialogComponent } from '@app/feature-modules/home/components/login-dialog/login-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(public dialog: MatDialog) {
  }

  openLoginDialog() {
    this.dialog.open(LoginDialogComponent, {
      width: '250px',
      data: {}
    });
  }

  openRegisterDialog() {
    this.dialog.open(RegisterDialogComponent, {
      width: '250px',
      data: {}
    });
  }

  closeAllDialogs() {
    this.dialog.closeAll();
  }

  openNewTopicDialog(categoryId: Number) {
    this.dialog.open(NewTopicDialogComponent, {
      width: '900',
      data: {categoryId: categoryId}
    });
  }

}
