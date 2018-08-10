import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HamrofoodmanduResturantModule } from './resturant/resturant.module';
import { HamrofoodmanduLocationModule } from './location/location.module';
import { HamrofoodmanduOpeningHourModule } from './opening-hour/opening-hour.module';
import { HamrofoodmanduCategoryModule } from './category/category.module';
import { HamrofoodmanduFoodModule } from './food/food.module';
import { HamrofoodmanduHFMOrderModule } from './hfm-order/hfm-order.module';
import { HamrofoodmanduDeliveryInfoModule } from './delivery-info/delivery-info.module';
import { HamrofoodmanduPaymentTypeModule } from './payment-type/payment-type.module';
import { HamrofoodmanduTrayModule } from './tray/tray.module';
import { HamrofoodmanduTrayFoodModule } from './tray-food/tray-food.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HamrofoodmanduResturantModule,
        HamrofoodmanduLocationModule,
        HamrofoodmanduOpeningHourModule,
        HamrofoodmanduCategoryModule,
        HamrofoodmanduFoodModule,
        HamrofoodmanduHFMOrderModule,
        HamrofoodmanduDeliveryInfoModule,
        HamrofoodmanduPaymentTypeModule,
        HamrofoodmanduTrayModule,
        HamrofoodmanduTrayFoodModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduEntityModule {}
