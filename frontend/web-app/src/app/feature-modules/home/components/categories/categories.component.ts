import {Component, OnInit} from '@angular/core';
import {CategoryService} from "@shared/services/category.service";
import {Category} from "@shared/models/Category";
import {Topic} from "@shared/models/Topic";
import {TopicsService} from "@shared/services/topics.service";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  categories: Category[];
  topics: Topic[];

  constructor(private categoryService: CategoryService, private topicsService: TopicsService) {
  }


  ngOnInit() {
    this.getCategories();

  }

  private getCategories = () => {
    this.categoryService.getAllCategories().subscribe((categories: any) => {
      this.categories = categories._embedded.categories;
      console.log(this.categories);

    });
  }


  private getTopics = (categoryName: string) => {
    this.topicsService.getTopicsByCategory(categoryName).subscribe((topics: any) =>{
      this.topics = topics._embedded.topics;
    });
  }

}
