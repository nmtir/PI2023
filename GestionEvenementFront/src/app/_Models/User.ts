  import { Role } from './Role';
import {Message} from "./Message";

export class User {
  userId: string;
  username:string;
  role: Role;
  messages:Message[];
  signaledMessages:Message[];
}
