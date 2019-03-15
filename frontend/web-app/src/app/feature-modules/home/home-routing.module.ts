import { TopicsComponent } from '@features/home/components/topics/topics.component';
import { PostsComponent } from './components/posts/posts.component';
import { NgModule, Component } from '@angular/core';
import {MainPageComponent} from './components/main-page/main-page.component';
import {RouterModule, Routes} from '@angular/router';
import {CategoriesComponent} from '@features/home/components/categories/categories.component';

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    children: [
      {
        path: '',

        component: CategoriesComponent,
        children: [
         { path: 'categories/:id',
          component: TopicsComponent,
          children: [
            {path: 'topics/:id',
            pathMatch: 'full',
            component: PostsComponent}
          ]}
        ]
      }
    ]
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
