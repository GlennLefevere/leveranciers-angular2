import {Leverancier} from './leverancier';

export class Artikel {
    id: number;
    omschrijving: string;
    leveranciers: Leverancier[];
}