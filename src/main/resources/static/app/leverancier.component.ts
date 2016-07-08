import { Component, OnInit, Input } from '@angular/core';
import { Router }           from '@angular/router';
import {ROUTER_DIRECTIVES} from '@angular/router';

import { LeverancierService } from './leverancier.service';
import { ArtikelService }        from './artikel.service';
import {Leverancier} from '.leverancier';
import {LevArt} from '.levart';

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
        this.text.levArts.forEach(la => {
            this.artikelService.getLeveranciersByArtikelId(la.artikel.id).then(lev => {
                la.artikel.leveranciers = lev;
            });
        });
    }

    deleteArtikel(levArt: LevArt) {
        var idx = this.text.levArts.indexOf(levArt);

        if (idx != -1) {
            this.text.levArts.splice(idx, 1);
        }

        this.leverancierService.removeArtikel(levArt.id);
    }

}