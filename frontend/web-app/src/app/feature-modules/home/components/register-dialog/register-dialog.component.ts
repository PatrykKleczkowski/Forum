import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { AuthService } from '../../../../shared/services/auth.service';
import { UserCredentials } from '../../../../shared/models';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent implements OnInit {

  hidingPassword = true;
  hidingPasswordConfirm = true;

  public registerForm: FormGroup;

  constructor(private authService: AuthService, public dialogRef: MatDialogRef<RegisterDialogComponent>) { }

  ngOnInit() {
    this.registerForm = new FormGroup({
      login: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl(null, Validators.required)
    });
  }

  register() {
    if (this.registerForm.valid) {
      this.authService
        .register(this.getRegistrationDetails())
        .subscribe(this.onSuccess, this.onFail);
    }
  }

  private getRegistrationDetails(): UserCredentials {
    const formValue = this.registerForm.value;

    return {
      username: formValue.login,
      password: formValue.password
    };
  }

  private onFail = () => {
    console.log('fail');
  }

  private onSuccess = () => {
    this.dialogRef.close();
  }
}
