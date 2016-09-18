import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {Artikel} from '.artikel';
import { Leverancier } from './leverancier';


@Injectable()
export class ArtikelService {

    private artikelsUrl = '/api/artikels';
    private levartUrl = '/api/artikels/levByArt';
    private artlevartUrl = '/api/artikels/artByLev'

    constructor(private http: Http) { }

    getArtikels(): Promise<Artikel[]> {
        let url = `${this.artikelsUrl}/`;

        console.log(url);

        return this.http.get(url)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    getLeveranciersByArtikelId(id: number): Promise<Leverancier[]> {
        let url = `${this.levartUrl}/${id}`;

        return this.http.get(url)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    getArtikelsByQuery(query: string): Promise<Artikel[]> {
        let url = `${this.artikelsUrl}/${query}`;

        return this.http.get(url)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    getArtikelsByLevId(id: string): Promise<Artikel[]> {
        let url = `${this.artlevartUrl}/${id}`;

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