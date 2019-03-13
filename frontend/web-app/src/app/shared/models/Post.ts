import {Topic} from "@shared/models/Topic";
import {User} from "@shared/models/user";
import {Comment} from "@shared/models/Comment";

export interface Post{
  id: number;
  postContent: string;
  createdDate: Date;
  topic: Topic;
  postAuthor: User;
  comments?: Comment[];
  likes: number;
  edited: boolean;
  
}
