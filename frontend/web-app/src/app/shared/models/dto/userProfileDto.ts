import {User} from '../user';
import {Topic} from '../Topic';
import {Post} from '../Post';

export interface UserProfileDto {
  user: User;
  topics?: Topic[];
  posts?: Post[];
}
