import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';

import { UserCredentials } from '@app/shared/models/user';
import { AuthService } from '@app/core/services/auth.service';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  public loginForm: FormGroup;
  loginFailed: boolean;

  constructor(private authService: AuthService, public dialogRef: MatDialogRef<LoginDialogComponent>) { }

  ngOnInit() {
    this.loginFailed = false;
    this.loginForm = new FormGroup({
      login: new FormControl(null),
      password: new FormControl(null),
    });
  }
    login() {
      this.authService
        .login(this.getUserCredentials())
        .subscribe(this.onSuccess, this.onFail);
    }

    private getUserCredentials(): UserCredentials {
      const formValue = this.loginForm.value;

      return {
        username: formValue.login,
        password: formValue.password
      };
    }

    private onSuccess = () => {
      this.loginFailed = false;
      this.dialogRef.close();
    }

    private onFail = (error) => {
      this.loginFailed = true;
    }
  }
