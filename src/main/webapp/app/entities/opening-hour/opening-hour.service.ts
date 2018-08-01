import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOpeningHour } from 'app/shared/model/opening-hour.model';

type EntityResponseType = HttpResponse<IOpeningHour>;
type EntityArrayResponseType = HttpResponse<IOpeningHour[]>;

@Injectable({ providedIn: 'root' })
export class OpeningHourService {
    private resourceUrl = SERVER_API_URL + 'api/opening-hours';

    constructor(private http: HttpClient) {}

    create(openingHour: IOpeningHour): Observable<EntityResponseType> {
        return this.http.post<IOpeningHour>(this.resourceUrl, openingHour, { observe: 'response' });
    }

    update(openingHour: IOpeningHour): Observable<EntityResponseType> {
        return this.http.put<IOpeningHour>(this.resourceUrl, openingHour, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOpeningHour>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOpeningHour[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
