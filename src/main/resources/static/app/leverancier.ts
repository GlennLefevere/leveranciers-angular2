import {Adres} from './adres';
import {Artikel} from './artikel';

export class Leverancier {
    id: number;
    naam: string;
    straat: string;
    telefoon: string;
   	fax: string;
   	email: string;
   	website: string;
   	latitude: number;
   	longtitude: number;
   	webshop: boolean;
   	adres: Adres;
   	artikels: Artikel[];
}