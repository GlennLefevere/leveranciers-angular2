import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Leverancier } from './leverancier';


@Injectable()
export class LeverancierService {

    private leveranciersUrl = '/api/leveranciers/';  // URL to web api

    constructor(private http: Http) { }

    getLeveranciers(): Promise<Leverancier[]> {
        return this.http.get(this.leveranciersUrl)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    getLeveranciersByQuery(query: string): Promise<Leverancier[]> {
        let url = `${this.leveranciersUrl}/${query}`;

        return this.http.get(url)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    save(leverancier: Leverancier): Promise<Leverancier> {
        if (leverancier.id) {
            return this.put(leverancier);
        }
        return this.post(leverancier);
    }

    removeArtikel(id: string, artikel: artikel) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        let url = `${this.leveranciersUrl}/${id}`;

        return this.http
            .put(url, JSON.stringify(artikel), { headers: headers })
            .toPromise()
            .then(() => artikel)
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

    private put(leverancier: Leverancier) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        let url = `${this.leveranciersUrl}/${leverancier.id}`;

        return this.http
            .put(url, JSON.stringify(leverancier), { headers: headers })
            .toPromise()
            .then(() => leverancier)
            .catch(this.handleError);
    }

    private post(leverancier: Leverancier): Promise<Leverancier> {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });

        return this.http
            .post(this.leveranciersUrl, JSON.stringify(leverancier), { headers: headers })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
}