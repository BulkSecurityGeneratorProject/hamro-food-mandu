import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import {
    DeliveryInfoComponent,
    DeliveryInfoDetailComponent,
    DeliveryInfoUpdateComponent,
    DeliveryInfoDeletePopupComponent,
    DeliveryInfoDeleteDialogComponent,
    deliveryInfoRoute,
    deliveryInfoPopupRoute
} from './';

const ENTITY_STATES = [...deliveryInfoRoute, ...deliveryInfoPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeliveryInfoComponent,
        DeliveryInfoDetailComponent,
        DeliveryInfoUpdateComponent,
        DeliveryInfoDeleteDialogComponent,
        DeliveryInfoDeletePopupComponent
    ],
    entryComponents: [
        DeliveryInfoComponent,
        DeliveryInfoUpdateComponent,
        DeliveryInfoDeleteDialogComponent,
        DeliveryInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduDeliveryInfoModule {}
