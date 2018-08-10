import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import {
    TrayFoodComponent,
    TrayFoodDeleteDialogComponent,
    TrayFoodDeletePopupComponent,
    TrayFoodDetailComponent,
    trayFoodPopupRoute,
    trayFoodRoute,
    TrayFoodUpdateComponent
} from './';

import { AddToTrayFoodComponent } from './add-to-tray-food.component';

const ENTITY_STATES = [...trayFoodRoute, ...trayFoodPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrayFoodComponent,
        TrayFoodDetailComponent,
        TrayFoodUpdateComponent,
        TrayFoodDeleteDialogComponent,
        TrayFoodDeletePopupComponent,
        AddToTrayFoodComponent
    ],
    entryComponents: [TrayFoodComponent, TrayFoodUpdateComponent, TrayFoodDeleteDialogComponent, TrayFoodDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduTrayFoodModule {}
