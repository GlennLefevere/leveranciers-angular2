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
    }
    
    searchLand(event){
    	this.adresService.getLandenByQuery(event.query).then(landen => this.resultLanden = landen);
    }
    
    handleLandDropdown(event){
    	this.adresService.getLandenByQuery(event.query).then(landen => this.resultLanden = landen);
    }
    
    landSelected(){
    	
    }

}