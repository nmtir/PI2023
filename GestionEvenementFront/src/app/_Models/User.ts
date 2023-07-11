  import { Role } from './Role';
import {Message} from "./Message";

export class User {
  userId: string;
  username:string;
  roles: Role[];
  messages:Message[];
  signaledMessages:Message[];
}
