import {Post} from "./Post";
import {User} from "./User";

export class Message {
  messageId: string;
  post:Post;
  datePub:Date;
  contenu:string;
  isBlocked:number;
  message:Message;
  messages:Message[];
  user:User;
  signalUsers:User[];
  likes:User[];
}
