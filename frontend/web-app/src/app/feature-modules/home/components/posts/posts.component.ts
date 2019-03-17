import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Post} from '@shared/models/Post';
import {PostService} from '@shared/services/post.service';
import {AngularEditorConfig} from "@kolkov/angular-editor";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService) {
  }

  public newPostForm: FormGroup;

  topicId: number;
  posts: Post[];

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
      this.posts = posts._embedded.posts;
      console.log(this.posts);
    });
  }

  private initNewPostForm() {
    this.newPostForm = new FormGroup({
      content: new FormControl()
    })
  }
}
