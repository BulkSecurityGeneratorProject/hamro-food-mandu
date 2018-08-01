import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import {
    ResturantComponent,
    ResturantDetailComponent,
    ResturantUpdateComponent,
    ResturantDeletePopupComponent,
    ResturantDeleteDialogComponent,
    resturantRoute,
    resturantPopupRoute
} from './';

const ENTITY_STATES = [...resturantRoute, ...resturantPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResturantComponent,
        ResturantDetailComponent,
        ResturantUpdateComponent,
        ResturantDeleteDialogComponent,
        ResturantDeletePopupComponent
    ],
    entryComponents: [ResturantComponent, ResturantUpdateComponent, ResturantDeleteDialogComponent, ResturantDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduResturantModule {}
