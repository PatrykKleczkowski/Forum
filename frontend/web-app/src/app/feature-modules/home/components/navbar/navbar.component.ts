import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { AuthService } from '@app/core/services/auth.service';
import { DialogService } from '@app/shared/services/dialog.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(public authService: AuthService, private dialogService: DialogService, public dialog: MatDialog) { }

  ngOnInit() {
  }

  loginDialog() {
    this.dialogService.openLoginDialog();
  }
  registerDialog() {
    this.dialogService.openRegisterDialog();
  }

  isLogged() {
    return this.authService.isLogged();
  }

  logout() {
    this.authService.logout();
  }

  isAdmin() {
    return this.authService.isAdmin();
  }
  }




