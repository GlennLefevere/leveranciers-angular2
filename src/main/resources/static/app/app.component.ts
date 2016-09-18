import { Component, AfterViewChecked }          from '@angular/core';
import { ROUTER_DIRECTIVES }  from '@angular/router';

import { LeverancierService }        from './leverancier.service';
import { ArtikelService }        from './artikel.service';
import { AdresService } from './adres.service';
import { TranslationService } from './translation.service';

declare var componentHandler: any;

@Component({
    selector: 'my-app',

    templateUrl: `app/app.component.html`,
    //styleUrls: ['app/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [
        LeverancierService,
        ArtikelService,
        AdresService,
        TranslationService
    ]
})
export class AppComponent {
    title = 'Home';

    ngAfterViewInit() {
        // Ensure material-design-lite effects are applied
        componentHandler.upgradeDom();

    }
}