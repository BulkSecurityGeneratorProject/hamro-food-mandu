import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrayFood } from 'app/shared/model/tray-food.model';

type EntityResponseType = HttpResponse<ITrayFood>;
type EntityArrayResponseType = HttpResponse<ITrayFood[]>;

@Injectable({ providedIn: 'root' })
export class TrayFoodService {
    private resourceUrl = SERVER_API_URL + 'api/tray-foods';

    constructor(private http: HttpClient) {}

    create(trayFood: ITrayFood): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trayFood);
        return this.http
            .post<ITrayFood>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(trayFood: ITrayFood): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trayFood);
        return this.http
            .put<ITrayFood>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITrayFood>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITrayFood[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(trayFood: ITrayFood): ITrayFood {
        const copy: ITrayFood = Object.assign({}, trayFood, {
            creationDate:
                trayFood.creationDate != null && trayFood.creationDate.isValid() ? trayFood.creationDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((trayFood: ITrayFood) => {
            trayFood.creationDate = trayFood.creationDate != null ? moment(trayFood.creationDate) : null;
        });
        return res;
    }
    findByTrayId(trayId?: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<ITrayFood[]>(`${this.resourceUrl}/tray/${trayId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
}
