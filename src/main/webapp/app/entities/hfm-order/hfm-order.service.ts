import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHFMOrder } from 'app/shared/model/hfm-order.model';

type EntityResponseType = HttpResponse<IHFMOrder>;
type EntityArrayResponseType = HttpResponse<IHFMOrder[]>;

@Injectable({ providedIn: 'root' })
export class HFMOrderService {
    private resourceUrl = SERVER_API_URL + 'api/hfm-orders';

    constructor(private http: HttpClient) {}

    create(hFMOrder: IHFMOrder): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(hFMOrder);
        return this.http
            .post<IHFMOrder>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(hFMOrder: IHFMOrder): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(hFMOrder);
        return this.http
            .put<IHFMOrder>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHFMOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHFMOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(hFMOrder: IHFMOrder): IHFMOrder {
        const copy: IHFMOrder = Object.assign({}, hFMOrder, {
            creationDate:
                hFMOrder.creationDate != null && hFMOrder.creationDate.isValid() ? hFMOrder.creationDate.format(DATE_FORMAT) : null,
            completionDate:
                hFMOrder.completionDate != null && hFMOrder.completionDate.isValid() ? hFMOrder.completionDate.format(DATE_FORMAT) : null,
            deliveryDate:
                hFMOrder.deliveryDate != null && hFMOrder.deliveryDate.isValid() ? hFMOrder.deliveryDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        res.body.completionDate = res.body.completionDate != null ? moment(res.body.completionDate) : null;
        res.body.deliveryDate = res.body.deliveryDate != null ? moment(res.body.deliveryDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((hFMOrder: IHFMOrder) => {
            hFMOrder.creationDate = hFMOrder.creationDate != null ? moment(hFMOrder.creationDate) : null;
            hFMOrder.completionDate = hFMOrder.completionDate != null ? moment(hFMOrder.completionDate) : null;
            hFMOrder.deliveryDate = hFMOrder.deliveryDate != null ? moment(hFMOrder.deliveryDate) : null;
        });
        return res;
    }

    findByUserId(userId: Number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IHFMOrder[]>(`${this.resourceUrl}/user/${userId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
}
