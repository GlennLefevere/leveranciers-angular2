import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent } from './app.component';
import { IndexComponent } from './index.component';
import { LeveranciersComponent } from './leverancier.component';
import { ArtikelComponent } from './artikel.component';
import { NewLeveranciersComponent } from './createleverancier.component';

import { LeverancierService } from './leverancier.service';
import { ArtikelService }        from './artikel.service';

import {TranslationComponent} from './translation.component';

import {AutoCompleteModule} from 'primeng/primeng';
import {DataTableModule, SharedModule} from 'primeng/primeng';
//import {Column} from 'primeng/primeng';
import {PanelModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {InputMaskModule} from 'primeng/primeng';

import { routing } from './app.routing';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        AutoCompleteModule,
        DataTableModule,
        SharedModule,
    //Column,
        PanelModule,
        ButtonModule,
        InputTextModule,
        InputMaskModule
    ],
    declarations: [
        AppComponent,
        IndexComponent,
        LeveranciersComponent,
        ArtikelComponent,
        NewLeveranciersComponent,
        TranslationComponent
    ],
    providers: [
        LeverancierService,
        ArtikelService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}