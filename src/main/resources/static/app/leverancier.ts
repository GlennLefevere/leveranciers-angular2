import {Artikel} from './artikel';
import {Land} from './land';
import {Gemeente} from './gemeente';
import {Provincie} from './provincie';

export class Leverancier {
    id: string;
    naam: string;
    straat: string;
    telefoon: string;
   	fax: string;
   	email: string;
   	website: string;
   	latitude: number;
   	longtitude: number;
   	webshop: boolean;
   	artikels: Artikel[];

   	land: Land;
   	gemeente: Gemeente;
   	provincie: Provincie;
}