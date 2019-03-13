import {Post} from "@shared/models/Post";
import {User} from "@shared/models/user";

export interface Comment{
  id: number;
  commentContent: string;
  createdDate: Date;
  post: Post;
  commentAuthor: User;
}
