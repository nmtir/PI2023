import { Role } from './Role';
import {Message} from "./Message";

export class User {
  userId: string;
  name:string;
  role: Role;
  messages:Message[];
  signaledMessages:Message[];
}
