import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tray } from 'app/shared/model/tray.model';
import { TrayService } from './tray.service';
import { TrayComponent } from './tray.component';
import { TrayDetailComponent } from './tray-detail.component';
import { TrayUpdateComponent } from './tray-update.component';
import { TrayDeletePopupComponent } from './tray-delete-dialog.component';
import { ITray } from 'app/shared/model/tray.model';
import { TrayListComponent } from 'app/entities/tray/tray-list/tray-list.component';

@Injectable({ providedIn: 'root' })
export class TrayResolve implements Resolve<ITray> {
    constructor(private service: TrayService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((tray: HttpResponse<Tray>) => tray.body));
        }
        return of(new Tray());
    }
}

export const trayRoute: Routes = [
    {
        path: 'tray',
        component: TrayComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray/:id/view',
        component: TrayDetailComponent,
        resolve: {
            tray: TrayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray/new',
        component: TrayUpdateComponent,
        resolve: {
            tray: TrayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray/:id/edit',
        component: TrayUpdateComponent,
        resolve: {
            tray: TrayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tray/list',
        component: TrayListComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trayPopupRoute: Routes = [
    {
        path: 'tray/:id/delete',
        component: TrayDeletePopupComponent,
        resolve: {
            tray: TrayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hamrofoodmanduApp.tray.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
