import { TopicsService } from '@shared/services/topics.service';
import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Params, } from '@angular/router';
import { Topic } from '@shared/models/Topic';
@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

topics: Topic[];
  constructor(private router: ActivatedRoute, private topicsService: TopicsService) {
  }
categoryId: number;
  ngOnInit() {
this.router.params.subscribe(params => {
  this.categoryId = params['id'];
  this.getListTopics(this.categoryId);
});
  }
  private getListTopics(id: number) {
    this.topicsService.getTopicsByCategory(id).subscribe((topics: any) => {
  this.topics = topics._embedded.topics;
    });
  }


}