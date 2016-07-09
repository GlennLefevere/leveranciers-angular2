import {Provincie} from './provincie';
import {Straat} from './straat';

export class Gemeente {
    id: number;
    naam: string;
    postcode: number;
    provincie: Provincie;
    straten: Straat[];
}