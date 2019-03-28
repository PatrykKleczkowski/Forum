import {Component, Input, OnInit} from '@angular/core';
import {CategoryService} from '@shared/services/category.service';
import {Category} from '@shared/models/Category';
import {Topic} from '@shared/models/Topic';
import {Router} from '@angular/router';


@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'watki', 'data'];
  categories: Category[];
  topics: Topic[];

  constructor(private categoryService: CategoryService,
              private _router: Router) {
  }

  @Input() category: Category;

  ngOnInit() {
    this.getCategories();
  }

  private getCategories = () => {
    this.categoryService.getAllCategories().subscribe((categories: any) => {
      this.categories = categories._embedded.categories;
      console.log(this.categories);

    });
  }

  getTopics = (category: Category) => {
    this._router.navigate([`/home/categories/`, category.id]);
  }
}

