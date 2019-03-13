import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
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
  MatTableModule,
  MatToolbarModule,
   MatExpansionModule,
  MatButtonToggleModule
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
    MatTableModule,
    FontAwesomeModule,
    MatToolbarModule,
    MatExpansionModule,
    MatButtonToggleModule
  ]
})
export class SharedModule { }
