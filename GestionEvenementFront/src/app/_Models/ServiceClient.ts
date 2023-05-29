import {Logistique} from "./Logistique";
import {Housing} from "./Housing";
import {Transport} from "./Transport";

export class ServiceClient {
   transportId:string;
   dateLimiteHousing:Date;
   dateLimitTransport:Date;
   logistique:Logistique;
   housings:Housing[];
   transports:Transport[];
}
