import { Component, OnInit, Input } from '@angular/core';
import { Router }           from '@angular/router';
import {ROUTER_DIRECTIVES} from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { LeverancierService } from './leverancier.service';
import {Leverancier} from './leverancier';
import {Land} from './land';
import {Gemeente} from './gemeente';
import {Provincie} from './provincie';

import {Button} from 'primeng/primeng';
import {InputText} from 'primeng/primeng';
import {InputMask} from 'primeng/primeng';

@Component({
    selector: 'prov-create-leverancier',
    templateUrl: 'app/createleverancier.component.html',
    directives: [Button, InputText, InputMask]
})

export class NewLeveranciersComponent implements OnInit {
    @Input() newLeverancier: Leverancier;

    constructor(
        private leverancierService: LeverancierService,
        private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.newLeverancier = new Leverancier();
        this.newLeverancier.gemeente = new Gemeente();
        this.newLeverancier.land = new Land();
        this.newLeverancier.provincie = new Provincie();
    }

    save() {
        this.leverancierService.save(this.newLeverancier);
    }

}