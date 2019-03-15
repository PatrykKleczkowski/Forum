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
import { CategoriesComponent } from './components/categories/categories.component';
import { TopicsComponent } from './components/topics/topics.component';
import { PostsComponent } from './components/posts/posts.component';

@NgModule({
  declarations: [
    LoginDialogComponent,
    RegisterDialogComponent,
    NavbarComponent,
    MainPageComponent,
    CategoriesComponent,
    TopicsComponent,
    PostsComponent
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
