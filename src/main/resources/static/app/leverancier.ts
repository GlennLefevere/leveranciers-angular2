import {LevArt} from './levart';
import {Adres} from './adres';

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
    levArts: LevArt[];
}