import { RouterModule } from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';

import {
  MatMenuModule,
  MatButtonModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatGridListModule,
  MatCardModule,
  MatTabsModule,
  MatChipsModule,
  MatDividerModule,
  MatListModule,
  MatTableModule
} from '@angular/material';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FontAwesomeModule,
    MatGridListModule,
    MatCardModule,
    MatTabsModule,
    MatChipsModule,
    MatDividerModule,
    MatListModule,
    MatTableModule,
    RouterModule
  ]
})
export class SharedModule {
}
