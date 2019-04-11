import { AuthService } from './../../../../core/services/auth.service';
import {TopicsService} from '@shared/services/topics.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Topic} from '@shared/models/Topic';
import {MatDialog} from "@angular/material";
import {DialogService} from "@shared/services/dialog.service";
import { Post } from '@app/shared/models';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  topics: Topic[] = [];
  newestPost: Post[] = [];
  constructor(private activatedRouter: ActivatedRoute, private topicsService: TopicsService,
              private router: Router, private dialogService: DialogService, private authService: AuthService) {
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
      for(let i of this.topics){
        this.getNewestPost(i.id);
      }
    });
  }

  getNewestPost(topicId: number){
    this.topicsService.getNewestPostByTopic(topicId).subscribe((post: Post) => {
      this.newestPost[topicId] = post;
    });
  }
  getPosts(topic: Topic) {
    this.router.navigate([`home/categories/`, this.categoryId, `topics`, topic.id]);
  }

  openNewTopicDialog() {
    this.dialogService.openNewTopicDialog(this.categoryId);
  }

  pinTopic(topicId: number){
    console.log("dupaa");
    this.topicsService.pinTopic(topicId).subscribe((resp: any) => {
    this.getListTopics(this.categoryId);
    });
  }

  isAdmin() {
    return this.authService.isAdmin();
  }
  redirectBack(){
    this.router.navigate([`home`]);
  }
}
