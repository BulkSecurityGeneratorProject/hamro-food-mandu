import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HFMOrder } from 'app/shared/model/hfm-order.model';
import { HFMOrderService } from './hfm-order.service';
import { HFMOrderComponent } from './hfm-order.component';
import { HFMOrderDetailComponent } from './hfm-order-detail.component';
import { HFMOrderUpdateComponent } from './hfm-order-update.component';
import { HFMOrderDeletePopupComponent } from './hfm-order-delete-dialog.component';
import { IHFMOrder } from 'app/shared/model/hfm-order.model';
import { HFMOrderListComponent } from 'app/entities/hfm-order/hm-order-list/hm-order-list.component';

@Injectable({ providedIn: 'root' })
export class HFMOrderResolve implements Resolve<IHFMOrder> {
    constructor(private service: HFMOrderService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((hFMOrder: HttpResponse<HFMOrder>) => hFMOrder.body));
        }
        return of(new HFMOrder());
    }
}

export const hFMOrderRoute: Routes = [
    {
        path: 'hfm-order',
        component: HFMOrderComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hfm-order/:id/view',
        component: HFMOrderDetailComponent,
        resolve: {
            hFMOrder: HFMOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hfm-order/new',
        component: HFMOrderUpdateComponent,
        resolve: {
            hFMOrder: HFMOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hfm-order/:id/edit',
        component: HFMOrderUpdateComponent,
        resolve: {
            hFMOrder: HFMOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hfm-order/list',
        component: HFMOrderListComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        }
    }
];

export const hFMOrderPopupRoute: Routes = [
    {
        path: 'hfm-order/:id/delete',
        component: HFMOrderDeletePopupComponent,
        resolve: {
            hFMOrder: HFMOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.hFMOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
