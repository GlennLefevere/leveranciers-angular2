import {Leverancier} from './leverancier';

export class Artikel {
    id: string;
    omschrijving: string;
    leveranciers: Leverancier[];
}