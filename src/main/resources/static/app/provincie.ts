import {Gemeente} from './gemeente';
import {Land} from './land';
	
export class Provincie{
	id: number;
	naam: string;
	land: Land;
	gemeentes: Gemeente[];
}