import { NgModule } from '@angular/core';
import {
  NavbarComponent,
  LoginDialogComponent,
  RegisterDialogComponent,
  MainPageComponent
} from './components';
import { SharedModule } from '@app/shared/modules/shared.module';
import { HomeRoutingModule } from '@app/feature-modules/home/home-routing.module';
import { DialogService } from '@app/shared/services/dialog.service';

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
