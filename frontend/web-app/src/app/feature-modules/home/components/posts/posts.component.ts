
import { UserService } from '@app/shared/services/user.service';
import { VoteService } from './../../../../shared/services/vote.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Post} from '@shared/models/Post';
import {PostService} from '@shared/services/post.service';
import {AngularEditorConfig} from "@kolkov/angular-editor";
import {FormControl, FormGroup} from "@angular/forms";
import { Topic } from '@app/shared/models/Topic';
import { AuthService } from '@app/core/services';
import { User } from '@app/shared/models/user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
    private authService: AuthService, private voteService: VoteService, private userService: UserService,
    private router: Router) {
  }

  public newPostForm: FormGroup;

  topicId: number;
  posts: Post[];

  topicName: string;
  authorId: number;
  bool: boolean;

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
this.isAuthor();
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

  isAuthor() {
    return this.postService.isTopicAuthor(this.topicId).subscribe(value => this.bool = value);
  }
  isLogged() {
    return this.authService.isLogged();
  }

  votePostUp(post: Post){
    return this.voteService.voteUp(post.id).subscribe((resp: any)=> {
      this.getListPosts(post.topic.id);
    });
  }
  votePostDown(post: Post){
    return this.voteService.voteDown(post.id).subscribe((resp: any)=> {
      this.getListPosts(post.topic.id);
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

isAdmin() {
  return this.authService.isAdmin();
}


deleteTopic(topicName: string){
  console.log(topicName);
 this.postService.deleteTopicByTitle(topicName).subscribe();
 this.router.navigate(['/home']);
}

}
