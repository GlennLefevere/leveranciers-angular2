import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Translation } from './translation';

@Injectable()
export class TranslationService {
    private adresUrl = '/api/translations';  // URL to web api

    constructor(private http: Http) { }

    getTranslation(key: string, locale: string): Promise<Translation> {
        let url = `${this.adresUrl}/${key}/${locale}`;

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