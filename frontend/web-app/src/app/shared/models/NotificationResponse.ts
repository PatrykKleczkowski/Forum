import {Post} from "@shared/models/Post";
import {User} from "@shared/models/user";

export interface NotificationResponse {

  id: number;
  displayed: boolean;
  title: string;
  message: string;
  post: Post;
  senderUser: User;
  notificationType: string;
  notificationDate: Date;
  categoryId: number;

}
