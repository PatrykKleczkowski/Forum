import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '@shared/services/category.service';
import {Category} from '@shared/models/Category';
import {Topic} from '@shared/models/Topic';
import {Router} from '@angular/router';
import {Post} from '@shared/models/Post';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'watki', 'id'];

  newestPost: Post[] = [];
  post: Post;
  programming: Category[] = [];
  scripts: Category[] = [];
  databases: Category[] = [];
  forum: Category[] = [];
  constructor(private categoryService: CategoryService,
              private _router: Router) {
  }

  ngOnInit() {
  this.getProgrammingCategories();
  }


  private getProgrammingCategories() {
    this.categoryService.getProgrammingCategories('Programowanie').subscribe((categories: any) => {
      this.programming = categories;
      this.getTopicWithNewestPostDate(categories);
    });

 this.categoryService.getProgrammingCategories('Język skryptowy').subscribe((categories: any) => {
      this.scripts = categories;
      this.getTopicWithNewestPostDate(categories);
});

this.categoryService.getProgrammingCategories('Bazy danych').subscribe((categories: any) => {
  this.databases = categories;
  this.getTopicWithNewestPostDate(categories);
});

this.categoryService.getProgrammingCategories('forum').subscribe((categories: any) => {
  this.forum = categories;
    this.getTopicWithNewestPostDate(categories);
});
}

  getTopics = (category: Category) => {
    this._router.navigate([`/home/categories`, category.id]);
  }

  getTopicWithNewestPostDate(categories: Category[]) {
    for(let i of categories){
    this.categoryService.getNewestPostDate(i.id).subscribe((post: Post) => {
      this.newestPost[i.id] = post;
    
    });
  }
}
goToUserProfile(username: string) {
  this._router.navigate([`/home/profile`, username]);
}
}
