import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Resturant } from 'app/shared/model/resturant.model';
import { ResturantService } from './resturant.service';
import { ResturantComponent } from './resturant.component';
import { ResturantDetailComponent } from './resturant-detail.component';
import { ResturantUpdateComponent } from './resturant-update.component';
import { ResturantDeletePopupComponent } from './resturant-delete-dialog.component';
import { IResturant } from 'app/shared/model/resturant.model';

@Injectable({ providedIn: 'root' })
export class ResturantResolve implements Resolve<IResturant> {
    constructor(private service: ResturantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((resturant: HttpResponse<Resturant>) => resturant.body));
        }
        return of(new Resturant());
    }
}

export const resturantRoute: Routes = [
    {
        path: 'resturant',
        component: ResturantComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.resturant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resturant/:id/view',
        component: ResturantDetailComponent,
        resolve: {
            resturant: ResturantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.resturant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resturant/new',
        component: ResturantUpdateComponent,
        resolve: {
            resturant: ResturantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.resturant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resturant/:id/edit',
        component: ResturantUpdateComponent,
        resolve: {
            resturant: ResturantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.resturant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resturantPopupRoute: Routes = [
    {
        path: 'resturant/:id/delete',
        component: ResturantDeletePopupComponent,
        resolve: {
            resturant: ResturantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.resturant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
