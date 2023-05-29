import {Forum} from "./Forum";
import {Message} from "./Message";
import {User} from "./User";

export class Post {
  postId: string;
  title:string;
  content:string;
  forum:Forum ;
  messages:Message[];
  user:User;
}
