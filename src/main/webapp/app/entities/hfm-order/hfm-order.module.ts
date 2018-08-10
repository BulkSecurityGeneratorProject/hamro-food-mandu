import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import { HamrofoodmanduAdminModule } from 'app/admin/admin.module';
import {
    HFMOrderComponent,
    HFMOrderDetailComponent,
    HFMOrderUpdateComponent,
    HFMOrderDeletePopupComponent,
    HFMOrderDeleteDialogComponent,
    hFMOrderRoute,
    hFMOrderPopupRoute
} from './';
import { HFMOrderListComponent } from './hm-order-list/hm-order-list.component';

const ENTITY_STATES = [...hFMOrderRoute, ...hFMOrderPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, HamrofoodmanduAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HFMOrderComponent,
        HFMOrderDetailComponent,
        HFMOrderUpdateComponent,
        HFMOrderDeleteDialogComponent,
        HFMOrderDeletePopupComponent,
        HFMOrderListComponent
    ],
    entryComponents: [HFMOrderComponent, HFMOrderUpdateComponent, HFMOrderDeleteDialogComponent, HFMOrderDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduHFMOrderModule {}
