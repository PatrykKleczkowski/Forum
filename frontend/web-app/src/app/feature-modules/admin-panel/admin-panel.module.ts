import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './components/user-list/user-list.component';
import { AdminLayoutComponent } from './components/admin-layout/admin-layout.component';
import { SharedModule } from '@app/shared/modules/shared.module';
import { AdminPanelRoutingModule } from '@app/feature-modules/admin-panel/admin-panel-routing.module';
import { UserService } from '@app/shared/services/user.service';

@NgModule({
  declarations: [UserListComponent, AdminLayoutComponent],
  imports: [
    SharedModule,
    AdminPanelRoutingModule
  ],
  providers: [
    UserService
  ]
})
export class AdminPanelModule { }
