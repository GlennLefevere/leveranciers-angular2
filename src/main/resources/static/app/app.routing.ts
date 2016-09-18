import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { LeveranciersComponent } from './leverancier.component';
import { ArtikelComponent } from './artikel.component';
import { NewLeveranciersComponent } from './createleverancier.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/index',
        pathMatch: 'full'
    },
    {
        path: 'index',
        component: IndexComponent
    },
    {
        path: 'artikels',
        component: ArtikelComponent
    },
    {
        path: 'leveranciers',
        component: LeveranciersComponent
    },
    {
        path: 'leveranciers/new',
        component: NewLeveranciersComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);