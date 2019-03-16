import {TopicsService} from '@shared/services/topics.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Topic} from '@shared/models/Topic';
import {MatDialog} from "@angular/material";
import {DialogService} from "@shared/services";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  topics: Topic[];

  constructor(private activatedRouter: ActivatedRoute, private topicsService: TopicsService,
              private router: Router, private dialogService: DialogService) {
  }

  categoryId: number;

  ngOnInit() {
    this.activatedRouter.params.subscribe(params => {
      this.categoryId = params['id'];
      this.getListTopics(this.categoryId);
    });
  }

  private getListTopics(id: number) {
    this.topicsService.getTopicsByCategory(id).subscribe((topics: any) => {
      this.topics = topics._embedded.topics;
    });
  }

  getPosts(topic: Topic) {
    this.router.navigate([`home/categories/`, this.categoryId, `topics`, topic.id]);
  }

  openNewTopicDialog() {
    this.dialogService.openNewTopicDialog(this.categoryId);
  }

}
