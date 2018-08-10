import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';

type EntityResponseType = HttpResponse<IDeliveryInfo>;
type EntityArrayResponseType = HttpResponse<IDeliveryInfo[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryInfoService {
    private resourceUrl = SERVER_API_URL + 'api/delivery-infos';

    constructor(private http: HttpClient) {}

    create(deliveryInfo: IDeliveryInfo): Observable<EntityResponseType> {
        return this.http.post<IDeliveryInfo>(this.resourceUrl, deliveryInfo, { observe: 'response' });
    }

    update(deliveryInfo: IDeliveryInfo): Observable<EntityResponseType> {
        return this.http.put<IDeliveryInfo>(this.resourceUrl, deliveryInfo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDeliveryInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDeliveryInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
