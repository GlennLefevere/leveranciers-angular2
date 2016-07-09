import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Land} from './land';

@Injectable()
export class AdresService {
	private adresUrl = '/api/adres/';  // URL to web api

    constructor(private http: Http) { }
    
    getLanden(): Promise<Land[]> {
        return this.http.get(this.leveranciersUrl)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    getLandenByQuery(query: string): Promise<Land[]> {
        let url = `${this.adresUrl}/${query}`;

        return this.http.get(url)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    private handleError(error: any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }
}