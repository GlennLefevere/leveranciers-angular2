import { provideRouter, RouterConfig }  from '@angular/router';

import { IndexComponent } from './index.component';
import { LeveranciersComponent } from './leverancier.component';
import { ArtikelComponent } from './artikel.component';

export const routes: RouterConfig = [
    {
        path: '',
        redirectTo: '/index',
        terminal: true
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
    }
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];