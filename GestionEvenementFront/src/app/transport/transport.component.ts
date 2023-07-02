import {Component} from '@angular/core';
import {Transport} from "../_Models/Transport";
import {TransportType} from "../_Models/TransportType";

@Component({
  selector: 'app-transport',
  templateUrl: './transport.component.html',
  styleUrls: ['./transport.component.css']
})
export class TransportComponent {
  selectedTransport:number
  vehicules:Transport[]=[];
  TripAStart: string;
  TripBStart: string;
  TripCStart: string;
  public selectForm(type: number): void {
    if (this.selectedTransport === type) {
      // If the clicked message is the same as the currently selected message,
      // close the input box by setting the selectedMessageId to null
      this.selectedTransport = null;
    } else {
      // If a different message is clicked, update the selectedMessageId
      this.selectedTransport = type;
    }
  }
 public addVehicle(type:number,Adress:string){
    let t:Transport
    console.log(type);
    console.log("////////////////////////////");
    console.log(Adress);

    t.startAdress=Adress;
    if (type==0)
      t.transportType=TransportType.MiniVan;
    if (type==1)
      t.transportType=TransportType.MiniBus;
    if (type==2)
      t.transportType=TransportType.Bus;
    this.vehicules.push(t);
    console.log(this.vehicules)
  }
}
