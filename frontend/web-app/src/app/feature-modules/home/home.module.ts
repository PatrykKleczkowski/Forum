import {NgModule} from '@angular/core';
import {LoginDialogComponent, MainPageComponent, NavbarComponent, RegisterDialogComponent} from './components';
import {SharedModule} from '@app/shared/modules/shared.module';
import {HomeRoutingModule} from '@app/feature-modules/home/home-routing.module';
import {DialogService} from '@app/shared/services/dialog.service';
import {CategoriesComponent} from './components/categories/categories.component';
import {TopicsComponent} from './components/topics/topics.component';
import {PostsComponent} from './components/posts/posts.component';
import {NewTopicDialogComponent} from './components/new-topic-dialog/new-topic-dialog.component';
import { UserRankListComponent } from './components/user-rank-list/user-rank-list.component';

import { TopicDisplayRankListComponent } from './components/topic-display-rank-list/topic-display-rank-list.component';
import { TopicNewestRankListComponent } from './components/topic-newest-rank-list/topic-newest-rank-list.component';
import { TopicWithMostLikesComponent } from './components/topic-with-most-likes/topic-with-most-likes.component';
import { PostService } from '@app/shared/services/post.service';
import { HomeComponent } from './home.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

@NgModule({
  declarations: [
    LoginDialogComponent,
    RegisterDialogComponent,
    NavbarComponent,
    MainPageComponent,
    CategoriesComponent,
    TopicsComponent,
    PostsComponent,
    NewTopicDialogComponent,
    UserRankListComponent,
    TopicDisplayRankListComponent,
    TopicNewestRankListComponent,
    TopicWithMostLikesComponent,
    HomeComponent,
    MyProfileComponent,
    UserProfileComponent
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
    RegisterDialogComponent,
    NewTopicDialogComponent
  ]
})
export class HomeModule {
}
