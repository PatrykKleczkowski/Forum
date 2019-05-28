import {UserService} from '@app/shared/services/user.service';
import {VoteService} from './../../../../shared/services/vote.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Post} from '@shared/models/Post';
import {PostService} from '@shared/services/post.service';
import {AngularEditorConfig} from "@kolkov/angular-editor";
import {FormControl, FormGroup} from "@angular/forms";
import {Topic} from '@app/shared/models/Topic';
import {AuthService} from '@app/core/services';
import {MatSnackBar} from '@angular/material';
import {CommentsService} from '@app/shared/services/comments.service';
import {TopicsService} from "@shared/services/topics.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
              private authService: AuthService, private voteService: VoteService, private userService: UserService,
              private router: Router, private snackBar: MatSnackBar,
              private commentsService: CommentsService, private topicService: TopicsService,
              private toastr: ToastrService) {
  }

  public newPostForm: FormGroup;
  public newCommentForm: FormGroup;
  topic: Topic;
  topicId: number;
  posts: Post[];

  authorId: number;
  bool: boolean;
  categoryId: number;
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
      this.getTopic(this.topicId);
      this.getListPosts(this.topicId);
    });
    this.initNewPostForm();
    this.initNewCommentForm();
  }

  private getListPosts(id: number) {
    this.postService.getPostsByTopic(id).subscribe((posts: any) => {
      this.posts = posts;
    });
  }

  private initNewPostForm() {
    this.newPostForm = new FormGroup({
      content: new FormControl()
    })
  }

  private getTopic(id: number) {
    this.topicService.getTopic(id).subscribe((topic: any) => {
      this.topic = topic;
    })
  }

  isAuthor() {
    return this.postService.isTopicAuthor(this.topicId).subscribe(value => this.bool = value);
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
    const topicTitle: string = this.topic.title;
    const content: string = this.newPostForm.value.content;


    this.openSnackBar("Dodano post, dziekujemy!", "OK");

    const alert: string = "dodano post do tematu " + topicTitle;
    this.toastr.success(alert);


    return this.postService.saveNewPost({topicTitle, content}).subscribe((resp: any) => {
      this.getListPosts(this.topicId);
    });
  }

  isAdmin() {
    return this.authService.isAdmin();
  }


  deleteTopic(topicName: string) {
    console.log(topicName);
    this.postService.deleteTopicByTitle(topicName).subscribe();
    this.router.navigate(['/home']);
  }


  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  private initNewCommentForm() {
    this.newCommentForm = new FormGroup({
      commentContent: new FormControl()
    })
  }

  saveComment(post_id: number) {
    const commentContent: string = this.newCommentForm.value.commentContent;
    return this.commentsService.saveNewComment({post_id, commentContent}).subscribe((resp: any) => {
      this.getListPosts(this.topicId);
    });
  }

  banAccount(post: Post) {
    this.userService.banUser(post.postAuthor.id)
      .subscribe((resp: any) => {
        this.getListPosts(post.topic.id);
      });
  }

  unbanAccount(post: Post) {
    this.userService.unbanUser(post.postAuthor.id)
      .subscribe((resp: any) => {
        this.getListPosts(post.topic.id);
      });
  }

  goToUserProfile(username: string) {
    this.router.navigate([`/home/profile`, username]);
  }
}
