import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import {
    FoodComponent,
    FoodDetailComponent,
    FoodUpdateComponent,
    FoodDeletePopupComponent,
    FoodDeleteDialogComponent,
    foodRoute,
    foodPopupRoute
} from './';

const ENTITY_STATES = [...foodRoute, ...foodPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FoodComponent, FoodDetailComponent, FoodUpdateComponent, FoodDeleteDialogComponent, FoodDeletePopupComponent],
    entryComponents: [FoodComponent, FoodUpdateComponent, FoodDeleteDialogComponent, FoodDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduFoodModule {}
