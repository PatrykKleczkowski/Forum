import {User} from "@shared/models/user";
import {Category} from "@shared/models/Category";
import {Post} from "@shared/models/Post";

export interface Topic{
  
  id: number;
  title: string;
  topicAuthor: User;
  category: Category;
  posts?: Post[];
  createdDate: Date;
  enabledForUsers: boolean;
  pinned: boolean;
}
