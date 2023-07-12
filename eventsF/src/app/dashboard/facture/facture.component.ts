import { Component, ViewEncapsulation } from '@angular/core';
import { HotToastService } from '@ngneat/hot-toast';
import { EventService } from 'src/app/services/event.service';
import { FactureService } from 'src/app/services/facture.service';
import * as html2pdf from 'html2pdf.js' 
import { ReservationService } from 'src/app/services/reservation.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-facture',
  templateUrl: './facture.component.html',
  styleUrls: ['./facture.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FactureComponent {
  reservations:any;
  reservation?:any;
  totala:any=0;
  date:any;
  id:any
  p: number = 1;
  total:any=0;
  constructor(private eventsService:EventService,private reservatioService:ReservationService,
    private factureService:FactureService,private toast: HotToastService) {}
  ngOnInit() {

    this.date=Date.now()
    this.getAll()


const element1 = document.getElementById("header1");
element1.setAttribute("hidden","true");
const element2 = document.getElementById("ftco-footer");
element2.setAttribute("hidden","true");
}
getAll(){
  
this.reservatioService.getAllReservation().subscribe(res=>{
  this.reservations=res;
})
}
ngOnDestroy() {
const element1 = document.getElementById("header1");
element1.removeAttribute("hidden");
const element2 = document.getElementById("ftco-footer");
element2.removeAttribute("hidden");
}
delete(id:any){
console.log(id);

  
 
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.reservatioService.delete(id).subscribe(res=>{
          this.toast.success('Réservation rejecter with success!!');
      this.getAll()
        })
      }
    })
  }
 
  

 
create(id){
  console.log(id);
  
  let data={
    payement:"chéque"
    
  }
 
  
  this.reservation=this.reservations.filter(res=>
    res.id==id
)
this.reservation=this.reservation[0]
console.log(this.reservation.user.id);

  this.factureService.addFacture(this.reservation.user.id,id,data).subscribe(res=>{

this.toast.success('Reservation created with success');
this.getAll()
  })

  


  
}

public voucher(id): void {  

this.total=0

  this.reservation=this.reservations.filter(res=>
      res.id==id
  )
  this.reservation=this.reservation[0]
  this.reservation?.event?.activites.forEach(element => {
    console.log(element);
    
    this.total=element.montant+this.total;
    console.log(this.total);
    
    
  });

   
  
 
  const option ={
    filename:"Voucher-"+Date.now()+".pdf",
    image:{
      type:'jpeg'
    },
    html2canavas:{},
    jsPDF:{orientation:'portrait'}

  };
  const content : Element=document.getElementById('content')
    html2pdf().from(content).set(option).save()
}
}
