import { Component, OnInit, Input } from '@angular/core';
import { Router }           from '@angular/router';
import {ROUTER_DIRECTIVES} from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { LeverancierService } from './leverancier.service';
import { AdresService } from './adres.service';

import {Leverancier} from './leverancier';
import {Adres} from './adres';
import {Land} from './land';
import {Provincie} from './provincie';
import {Gemeente} from './gemeente';
import {Straat} from './straat';

import {AutoComplete} from 'primeng/primeng';
import {Button} from 'primeng/primeng';
import {InputText} from 'primeng/primeng';
import {InputMask} from 'primeng/primeng';

@Component({
    selector: 'prov-create-leverancier',
    templateUrl: 'app/createleverancier.component.html',
    directives: [Button, InputText, InputMask, AutoComplete]
})

export class NewLeveranciersComponent implements OnInit {
    @Input() newLeverancier: Leverancier;

    land: Land;
    resultLanden: Land[];
    noLandSelected: boolean = true;

    provincie: Provincie;
    resultProvincies: Provincie[];
    noProvincieSelected: boolean = true;

    gemeente: Gemeente;
    resultGemeentes: Gemeente[];
    noGemeenteSelected: boolean = true;

    straat: Straat;
    resultStraten: Straat[];

    constructor(
        private leverancierService: LeverancierService,
        private adresService: AdresService,
        private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.newLeverancier = new Leverancier();
        this.newLeverancier.adres = new Adres();
    }

    save() {
        this.leverancierService.save(this.newLeverancier);
        window.history.back();
    }

    searchLand(event) {
        this.adresService.getLandenByQuery(event.query).then(landen => this.resultLanden = landen);
    }

    landSelectedMethod() {
        this.noLandSelected = false;
        this.newLeverancier.adres.landNaam = this.land.naam;
    }

    searchProvincie(event) {
        this.resultProvincies = this.land.provincies.filter(p => { if (p.naam.toUpperCase().indexOf(event.query.toUpperCase()) >= 0) return p });
    }

    provincieSelectedMethod() {
        this.noProvincieSelected = false;
        this.newLeverancier.adres.provincieNaam = this.provincie.naam;
    }

    searchGemeente(event) {
        this.resultGemeentes = this.provincie.gemeentes.filter(g => { if (g.naam.toUpperCase().indexOf(event.query.toUpperCase()) >= 0) return g });
    }

    gemeenteSelectedMethod() {
        this.noGemeenteSelected = false;
        this.newLeverancier.adres.gemeenteNaam = this.gemeente.naam;
        this.newLeverancier.adres.postcode = this.gemeente.postcode;
    }

   	searchStraat(event) {
        this.resultStraten = this.gemeente.straten.filter(s => { if (s.naam.toUpperCase().indexOf(event.query.toUpperCase()) >= 0) return s });
    }

    straatSelectedMethod() {
        this.newLeverancier.adres.straat = this.straat.naam;
    }


}