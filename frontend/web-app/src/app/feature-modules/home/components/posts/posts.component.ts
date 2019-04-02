import {VoteService} from './../../../../shared/services/vote.service';
import {AuthService} from '@app/shared/services/auth.service';
import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Post} from '@shared/models/Post';
import {PostService} from '@shared/services/post.service';
import {AngularEditorConfig} from "@kolkov/angular-editor";
import {FormControl, FormGroup} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {CommentsService} from "@shared/services/comments.service";
import {Comment} from "@shared/models/Comment";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
              private authService: AuthService, private voteService: VoteService, private snackBar: MatSnackBar,
              private commentsService: CommentsService) {
  }

  public newPostForm: FormGroup;
  public newCommentForm: FormGroup;

  topicId: number;
  posts: Post[];
  comments: Comment[];

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
    });
    this.initNewPostForm();
    this.initNewCommentForm();
  }

  private getListPosts(id: number) {
    this.postService.getPostsByTopic(id).subscribe((posts: any) => {
      this.posts = posts;
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

  votePostUp(post: Post) {
    return this.voteService.voteUp(post.id).subscribe((resp: any) => {
      this.getListPosts(post.topic.id);
    });
  }

  votePostDown(post: Post) {
    return this.voteService.voteDown(post.id).subscribe((resp: any) => {
      this.getListPosts(post.topic.id);
    });
  }

  savePost() {
    const topicTitle: string = this.topicName;
    const content: string = this.newPostForm.value.content;

    this.openSnackBar("Dodano post, dziekujemy!", "OK");

    return this.postService.saveNewPost({topicTitle, content}).subscribe();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  fetchComments(post_id: number) {
    this.commentsService.getCommentsByPost(post_id).subscribe((comments: any) => {
      this.comments = comments._embedded.comments;
    });

    console.log(post_id);
    console.log(this.comments);
  }

  private initNewCommentForm() {
    this.newCommentForm = new FormGroup({
      commentContent: new FormControl()
    })
  }


}
