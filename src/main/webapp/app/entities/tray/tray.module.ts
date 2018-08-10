import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HamrofoodmanduSharedModule } from 'app/shared';
import { HamrofoodmanduAdminModule } from 'app/admin/admin.module';
import {
    TrayComponent,
    TrayDetailComponent,
    TrayUpdateComponent,
    TrayDeletePopupComponent,
    TrayDeleteDialogComponent,
    trayRoute,
    trayPopupRoute
} from './';
import { TrayListComponent } from './tray-list/tray-list.component';

const ENTITY_STATES = [...trayRoute, ...trayPopupRoute];

@NgModule({
    imports: [HamrofoodmanduSharedModule, HamrofoodmanduAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrayComponent,
        TrayDetailComponent,
        TrayUpdateComponent,
        TrayDeleteDialogComponent,
        TrayDeletePopupComponent,
        TrayListComponent
    ],
    entryComponents: [TrayComponent, TrayUpdateComponent, TrayDeleteDialogComponent, TrayDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduTrayModule {}
