import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OpeningHour } from 'app/shared/model/opening-hour.model';
import { OpeningHourService } from './opening-hour.service';
import { OpeningHourComponent } from './opening-hour.component';
import { OpeningHourDetailComponent } from './opening-hour-detail.component';
import { OpeningHourUpdateComponent } from './opening-hour-update.component';
import { OpeningHourDeletePopupComponent } from './opening-hour-delete-dialog.component';
import { IOpeningHour } from 'app/shared/model/opening-hour.model';

@Injectable({ providedIn: 'root' })
export class OpeningHourResolve implements Resolve<IOpeningHour> {
    constructor(private service: OpeningHourService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((openingHour: HttpResponse<OpeningHour>) => openingHour.body));
        }
        return of(new OpeningHour());
    }
}

export const openingHourRoute: Routes = [
    {
        path: 'opening-hour',
        component: OpeningHourComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.openingHour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opening-hour/:id/view',
        component: OpeningHourDetailComponent,
        resolve: {
            openingHour: OpeningHourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.openingHour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opening-hour/new',
        component: OpeningHourUpdateComponent,
        resolve: {
            openingHour: OpeningHourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.openingHour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opening-hour/:id/edit',
        component: OpeningHourUpdateComponent,
        resolve: {
            openingHour: OpeningHourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.openingHour.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const openingHourPopupRoute: Routes = [
    {
        path: 'opening-hour/:id/delete',
        component: OpeningHourDeletePopupComponent,
        resolve: {
            openingHour: OpeningHourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.openingHour.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
