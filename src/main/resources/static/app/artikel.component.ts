import { Component, OnInit, Input, ApplicationRef, ChangeDetectorRef } from '@angular/core';
import { Router }           from '@angular/router';
import {ROUTER_DIRECTIVES} from '@angular/router';

import {Artikel} from '.artikel';
import { Leverancier } from './leverancier';

import { LeverancierService } from './leverancier.service';
import { ArtikelService }        from './artikel.service';

import {TranslationComponent} from './translation.component';

//import {DataTable} from 'primeng/primeng';
//import {Column} from 'primeng/primeng';
//import {Button} from 'primeng/primeng';
//import {Checkbox} from 'primeng/primeng';

declare var componentHandler: any;

@Component({
    selector: 'prov-artikels',
    templateUrl: 'app/artikel.component.html'//,
    //directives: [DataTable, Column, Button, Checkbox, ROUTER_DIRECTIVES, TranslationComponent]
})
export class ArtikelComponent implements OnInit {
    artikels: Artikel[];

    selectedArtikels: Artikel[] = [];
    selectedLeveranciers: Leverancier[] = [];

    constructor(
        private router: Router,
        private leverancierService: LeverancierService,
        private artikelService: ArtikelService,
        private appRef: ApplicationRef,
        private cd: ChangeDetectorRef) { }

    ngOnInit() {
        this.artikelService.getArtikels().then(arts => {

            this.artikels = arts;
            this.artikels.forEach(a => {
                this.artikelService.getLeveranciersByArtikelId(a.id).then(lev => {
                    a.leveranciers = lev;

                    a.leveranciers.forEach(l => {
                        this.artikelService.getArtikelsByLevId(l.id).then(ar => l.artikels = ar);
                    });
                });
            });

            this.selectedArtikels.push(arts[1]);

            componentHandler.upgradeDom();
        });
    }

    changeLevBox() {
        console.log(this.selectedLeveranciers);
    }

    checkboxChanged(artikel: Artikel) {
        if (this.selectedArtikels.indexOf(artikel) >= 0) {
            artikel.leveranciers.forEach(l => {
                if (this.selectedLeveranciers.indexOf(l) < 0) {
                    this.selectedLeveranciers.push(l.id);
                }
            });
        } else {
            artikel.leveranciers.forEach(l => {
                let index = this.selectedLeveranciers.indexOf(l);
                this.selectedLeveranciers.splice(index, 1);
            });
        }
        this.cd.markForCheck();

        console.log(this.selectedLeveranciers);
    }

    onRowSelect(event) {
        console.log(event.data);
        event.data.leveranciers.forEach(l => {
            if (this.selectedLeveranciers.indexOf(l) < 0) {
                this.selectedLeveranciers.push(l.id);
            }
        });
        this.cd.markForCheck();
    }

    onRowUnselect(event) {
        console.log(event.data);
    }

}