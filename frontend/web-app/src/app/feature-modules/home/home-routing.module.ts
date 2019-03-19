import { UserRankListComponent } from './components/user-rank-list/user-rank-list.component';
import {PostsComponent} from './components/posts/posts.component';
import {NgModule} from '@angular/core';
import {MainPageComponent} from './components/main-page/main-page.component';
import {RouterModule, Routes} from '@angular/router';
import {TopicsComponent} from '@features/home/components/topics/topics.component';
import {CategoriesComponent} from '@features/home/components/categories/categories.component';
import { TopicDisplayRankListComponent } from './components/topic-display-rank-list/topic-display-rank-list.component';

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: CategoriesComponent,
      },
      {
        path: '',
        component: UserRankListComponent
      },
      {
        path: '',
        component: TopicDisplayRankListComponent
      },
      {
        path: 'categories/:id',
        pathMatch: 'full',
        component: TopicsComponent,
      },
      {
        path: 'categories/:id/topics/:id',
        pathMatch: 'full',
        component: PostsComponent
      }
    ]
  },


];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
