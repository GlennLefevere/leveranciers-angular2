import { Component }          from '@angular/core';
import { ROUTER_DIRECTIVES }  from '@angular/router';

import { LeverancierService }        from './leverancier.service';
import { ArtikelService }        from './artikel.service';

@Component({
    selector: 'my-app',

    template: `
    <h1>{{title}}</h1>
    <nav>
      <a [routerLink]="['/index']" routerLinkActive="active">Home</a>
      <a [routerLink]="['/leveranciers']" routerLinkActive="active">Leveranciers</a>
      <a [routerLink]="['/artikels']" routerLinkActive="active">Artikels</a>
    </nav>
    <router-outlet></router-outlet>
  `,
    styleUrls: ['app/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [
        LeverancierService,
        ArtikelService
    ]
})
export class AppComponent {
    title = 'Home';
}