import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DeliveryInfo } from 'app/shared/model/delivery-info.model';
import { DeliveryInfoService } from './delivery-info.service';
import { DeliveryInfoComponent } from './delivery-info.component';
import { DeliveryInfoDetailComponent } from './delivery-info-detail.component';
import { DeliveryInfoUpdateComponent } from './delivery-info-update.component';
import { DeliveryInfoDeletePopupComponent } from './delivery-info-delete-dialog.component';
import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';

@Injectable({ providedIn: 'root' })
export class DeliveryInfoResolve implements Resolve<IDeliveryInfo> {
    constructor(private service: DeliveryInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((deliveryInfo: HttpResponse<DeliveryInfo>) => deliveryInfo.body));
        }
        return of(new DeliveryInfo());
    }
}

export const deliveryInfoRoute: Routes = [
    {
        path: 'delivery-info',
        component: DeliveryInfoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.deliveryInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-info/:id/view',
        component: DeliveryInfoDetailComponent,
        resolve: {
            deliveryInfo: DeliveryInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.deliveryInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-info/new',
        component: DeliveryInfoUpdateComponent,
        resolve: {
            deliveryInfo: DeliveryInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.deliveryInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-info/:id/edit',
        component: DeliveryInfoUpdateComponent,
        resolve: {
            deliveryInfo: DeliveryInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.deliveryInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const deliveryInfoPopupRoute: Routes = [
    {
        path: 'delivery-info/:id/delete',
        component: DeliveryInfoDeletePopupComponent,
        resolve: {
            deliveryInfo: DeliveryInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.deliveryInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
