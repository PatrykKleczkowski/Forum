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
  categories: Category[]  = [];
  newestPost: Post[] = [];
  post: Post;
  constructor(private categoryService: CategoryService,
              private _router: Router) {
  }

  ngOnInit() {
    this.getCategories();
  }

  private getCategories = () => {
    this.categoryService.getAllCategories().subscribe((categories: any) => {
      this.categories = categories._embedded.categories;
      for(let i of this.categories){
       this.getTopicWithNewestPostDate(i.id);
      }
    });
  }

  getTopics = (category: Category) => {
    this._router.navigate([`/home/categories`, category.id]);
  }

  getTopicWithNewestPostDate(categoryId: number) {
    this.categoryService.getNewestPostDate(categoryId).subscribe((post: Post) => {
      this.newestPost[categoryId] = post;

    });
  }
}

