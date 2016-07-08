import { Component, OnInit, Input } from '@angular/core';
import { Router }           from '@angular/router';
import {ROUTER_DIRECTIVES} from '@angular/router';

import { LeverancierService } from './leverancier.service';
import { ArtikelService }        from './artikel.service';
import {Leverancier} from '.leverancier';

import {AutoComplete} from 'primeng/primeng';
import {DataTable} from 'primeng/primeng';
import {Column} from 'primeng/primeng';
import {Panel} from 'primeng/primeng';
import {Button} from 'primeng/primeng';

@Component({
    selector: 'prov-leverancier',
    templateUrl: 'app/leverancier.component.html',
    directives: [AutoComplete, DataTable, Column, Panel, Button]
})

export class LeveranciersComponent {
    text: Leverancier;
    results: Leveranciers[];
    selected: boolean = false;
    collapsed: boolean = true;

    constructor(
        private router: Router,
        private leverancierService: LeverancierService,
        private artikelService: ArtikelService) { }

    search(event) {
        this.selected = false;
        this.collapsed = true;
        this.leverancierService.getLeveranciersByQuery(event.query)
            .then(leveranciers => this.results = leveranciers);
    }

    handleDropdown(event) {
        this.leverancierService.getLeveranciersByQuery(event.query)
            .then(leveranciers => this.results = leveranciers);
    }

    itemSelected() {
        this.selected = true;
    }

    toggle(event) {
        //test: artikel[];
        var test: any[] = [];

        this.text.artikels.forEach(a => {
            this.artikelService.getLeveranciersByArtikelId(a.id).then(lev => {
                a.leveranciers = lev;
                test.push(a);
            });
        });

        this.text.artikels = test;
    }

    deleteArtikel(art: Artikel) {
        var idx = this.text.artikels.indexOf(art);
        if (idx != -1) {
            this.text.artikels = this.text.artikels.splice(idx, 1); // The second parameter is the number of elements to remove.
        }

        this.leverancierService.removeArtikel(this.text.id, art);
    }

}