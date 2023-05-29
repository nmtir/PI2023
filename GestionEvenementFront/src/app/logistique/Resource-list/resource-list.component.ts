import {Component, OnInit} from '@angular/core';
import {Logistique} from "../../_Models/Logistique";
import {LogistiqueService} from "../../_Services/logistique.service";
import {first} from "rxjs";
import {ResourceMaterielService} from "../../_Services/resource-materiel.service";
import {ResourceMateriel} from "../../_Models/ResourceMateriel";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-logistique-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css']
})
export class ResourceListComponent implements OnInit{
  id : string;
  resource: ResourceMateriel;
  constructor(
    private route: ActivatedRoute,
    private resourceService: ResourceMaterielService
  ){}
  ngOnInit() {
    this.loadResource();
  }

  private loadResource() {

      this.route.paramMap.subscribe(params => {
        this.id = params.get('id');
      });
    this.resourceService.getById(this.id).pipe(first()).subscribe(res => {
      const newObj: any = res;
      console.log(newObj)
      this.resource = newObj;
    });

    return;

  }


}
