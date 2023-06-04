import { Role } from './Role';
import {Message} from "./Message";

export class User {
  idUser: string;
  name:string;
  role: Role;
  messages:Message[];
  signaledMessages:Message[];
}
