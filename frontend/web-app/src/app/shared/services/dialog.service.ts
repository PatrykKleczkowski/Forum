import {Injectable} from '@angular/core';
import {MatDialog} from '@angular/material';
import {LoginDialogComponent, RegisterDialogComponent} from '../../feature-modules/home/components';
import {NewTopicDialogComponent} from "@features/home/components/new-topic-dialog/new-topic-dialog.component";

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
