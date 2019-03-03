import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  NavbarComponent,
  LoginDialogComponent,
  RegisterDialogComponent,
  MainPageComponent
} from './components';
import { HomeRoutingModule } from './home-routing.module';

@NgModule({
  declarations: [
    LoginDialogComponent,
    RegisterDialogComponent,
    NavbarComponent,
    MainPageComponent
  ],
  imports: [
    SharedModule,
    HomeRoutingModule
  ],
  providers: [
    DialogService
  ],
  entryComponents: [
    LoginDialogComponent,
    RegisterDialogComponent
  ]
})
export class HomeModule { }
