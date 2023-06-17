import {Forum} from "./Forum";
import {Message} from "./Message";
import {User} from "./User";

export class Post {
  postId: number;
  title:string;
  content:string;
  forum:Forum ;
  datePub:Date;
  messages:Message[];
  user:User;
  views:User[];
  likes:User[];
}
