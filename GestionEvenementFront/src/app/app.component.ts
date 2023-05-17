import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  title = 'GestionEvenementFront';
  ngOnInit() {
    this.loadStyle("src/assets/css/gApi.css");
    this.loadStyle("src/assets/css/bootstrap.min.css");
    this.loadStyle("src/assets/css/bootstrap-datepicker.css");
    this.loadStyle("src/assets/css/flaticon.css");
    this.loadStyle("src/assets/css/icomoon.css");
    this.loadStyle("src/assets/css/ionicons.min.css");
    this.loadStyle("src/assets/css/jquery.timepicker.css");
    this.loadStyle("src/assets/css/magnific-popup.css");
    this.loadStyle("src/assets/css/open-iconic-bootstrap.min.css");
    this.loadStyle("src/assets/css/owl.carousel.min.css");
    this.loadStyle("src/assets/css/owl.theme.default.min.css");
    this.loadStyle("src/assets/css/style.css");
    this.loadStyle("src/assets/css/aos.css");
    this.loadStyle("src/assets/css/animate.css");

    this.loadScript("src/assets/js/jquery.min.js",false,false);
    this.loadScript("src/assets/js/aos.js",false,false);
    this.loadScript("src/assets/js/bootstrap.min.js",false,false);
    this.loadScript("src/assets/js/bootstrap-datepicker.js",false,false);
    this.loadScript("src/assets/js/jquery.animateNumber.min.js",false,false);
    this.loadScript("src/assets/js/jquery.easing.1.3.js",false,false);
    this.loadScript("src/assets/js/jquery.magnific-popup.min.js",false,false);
    this.loadScript("src/assets/js/owl.carousel.min.js",false,false);
    this.loadScript("src/assets/js/popper.min.js",false,false);
    this.loadScript("src/assets/js/jquery.stellar.min.js",false,false);
    this.loadScript("src/assets/js/jquery.waypoints.min.js",false,false);
    this.loadScript("src/assets/js/jquery-migrate-3.0.1.min.js",false,false);
    this.loadScript("src/assets/js/jquery-migrate-3.0.1.min.js",false,false);
    this.loadScript("src/assets/js/scrollax.min.js",false,false);
    this.loadScript("src/assets/js/main.js",false,false);
  }
  loadScript(src:string,sync:boolean,defer:boolean) {
    const node = document.createElement('script');
    node.src = src;
    node.type = 'text/javascript';
    node.async = sync;
    node.defer=defer;
    document.getElementsByTagName('body')[0].appendChild(node);
  }
loadStyle(src:string) {
    const node = document.createElement('link');
    node.href = src;
    node.rel = 'stylesheet';
    document.getElementsByTagName('head')[0].appendChild(node);
  }
}
