import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TrayFood } from 'app/shared/model/tray-food.model';
import { TrayFoodService } from './tray-food.service';
import { TrayFoodComponent } from './tray-food.component';
import { TrayFoodDetailComponent } from './tray-food-detail.component';
import { TrayFoodUpdateComponent } from './tray-food-update.component';
import { TrayFoodDeletePopupComponent } from './tray-food-delete-dialog.component';
import { ITrayFood } from 'app/shared/model/tray-food.model';
import { AddToTrayFoodComponent } from 'app/entities/tray-food/add-to-tray-food.component';

@Injectable({ providedIn: 'root' })
export class TrayFoodResolve implements Resolve<ITrayFood> {
    constructor(private service: TrayFoodService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((trayFood: HttpResponse<TrayFood>) => trayFood.body));
        }
        return of(new TrayFood());
    }
}

export const trayFoodRoute: Routes = [
    {
        path: 'tray-food',
        component: TrayFoodComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray-food/:id/view',
        component: TrayFoodDetailComponent,
        resolve: {
            trayFood: TrayFoodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray-food/new',
        component: TrayFoodUpdateComponent,
        resolve: {
            trayFood: TrayFoodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray-food/:id/add',
        component: AddToTrayFoodComponent,
        data: {
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray-food/:id/edit',
        component: TrayFoodUpdateComponent,
        resolve: {
            trayFood: TrayFoodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trayFoodPopupRoute: Routes = [
    {
        path: 'tray-food/:id/delete',
        component: TrayFoodDeletePopupComponent,
        resolve: {
            trayFood: TrayFoodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.trayFood.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
