import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import {
    OpeningHourComponent,
    OpeningHourDetailComponent,
    OpeningHourUpdateComponent,
    OpeningHourDeletePopupComponent,
    OpeningHourDeleteDialogComponent,
    openingHourRoute,
    openingHourPopupRoute
} from './';

const ENTITY_STATES = [...openingHourRoute, ...openingHourPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OpeningHourComponent,
        OpeningHourDetailComponent,
        OpeningHourUpdateComponent,
        OpeningHourDeleteDialogComponent,
        OpeningHourDeletePopupComponent
    ],
    entryComponents: [OpeningHourComponent, OpeningHourUpdateComponent, OpeningHourDeleteDialogComponent, OpeningHourDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduOpeningHourModule {}
