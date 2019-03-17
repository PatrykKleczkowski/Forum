import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {
  MatButtonModule, MatButtonToggleModule,
  MatCardModule,
  MatChipsModule,
  MatDialogModule,
  MatDividerModule,
  MatFormFieldModule,
  MatGridListModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatTableModule,
  MatTabsModule,
  MatIconModule,
  MatToolbarModule
} from '@angular/material';
import {AngularEditorModule} from "@kolkov/angular-editor";
import {HttpClientModule} from "@angular/common/http";

//import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

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
    //FontAwesomeModule,
    MatGridListModule,
    MatCardModule,
    MatTabsModule,
    MatChipsModule,
    MatDividerModule,
    MatListModule,
    MatTableModule,
    RouterModule,
    AngularEditorModule,
    HttpClientModule,
    MatButtonToggleModule,
    MatToolbarModule
  ]
})
export class SharedModule {
}
