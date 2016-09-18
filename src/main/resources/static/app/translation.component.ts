import { Component, OnInit, Input } from '@angular/core';

import { TranslationService } from './translation.service';
import { Translation } from './translation';

@Component({
    selector: 'translation',
    inputs: ['key'],
    templateUrl: 'app/translation.component.html',
})

export class TranslationComponent {
    translatedValue: string;
    public key: string;

    constructor(private translationService: TranslationService) { }

    ngOnInit() {
        this.translationService.getTranslation(this.key, "nl_BE").then(tekst => this.translatedValue = tekst.tekst);
    }
}