import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResturant } from 'app/shared/model/resturant.model';

type EntityResponseType = HttpResponse<IResturant>;
type EntityArrayResponseType = HttpResponse<IResturant[]>;

@Injectable({ providedIn: 'root' })
export class ResturantService {
    private resourceUrl = SERVER_API_URL + 'api/resturants';

    constructor(private http: HttpClient) {}

    create(resturant: IResturant): Observable<EntityResponseType> {
        return this.http.post<IResturant>(this.resourceUrl, resturant, { observe: 'response' });
    }

    update(resturant: IResturant): Observable<EntityResponseType> {
        return this.http.put<IResturant>(this.resourceUrl, resturant, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResturant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResturant[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
