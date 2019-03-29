import { AuthService } from '@app/shared/services/auth.service';
import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Post} from '@shared/models/Post';
import {PostService} from '@shared/services/post.service';
import {AngularEditorConfig} from "@kolkov/angular-editor";
import {FormControl, FormGroup} from "@angular/forms";
import { Topic } from '@app/shared/models/Topic';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
    private authService: AuthService) {
  }

  public newPostForm: FormGroup;

  topicId: number;
  posts: Post[];

  topicName: string;

  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '10rem',
    minHeight: '5rem',
    placeholder: 'Enter text here...',
    translate: 'no'
  };

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.topicId = params['id'];
      this.getListPosts(this.topicId);
      console.log(this.topicId);
    });
    this.initNewPostForm();
  }

  private getListPosts(id: number) {
    this.postService.getPostsByTopic(id).subscribe((posts: any) => {
      this.posts = posts;
      console.log(this.posts);
    this.topicName = this.posts[0].topic.title;
  });

  }

  private initNewPostForm() {
    this.newPostForm = new FormGroup({
      content: new FormControl()
    })
  }

  isLogged() {
    return this.authService.isLogged();
  }

}
