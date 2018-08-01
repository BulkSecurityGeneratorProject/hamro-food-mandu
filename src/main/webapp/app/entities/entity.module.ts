import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HamrofoodmanduResturantModule } from './resturant/resturant.module';
import { HamrofoodmanduLocationModule } from './location/location.module';
import { HamrofoodmanduOpeningHourModule } from './opening-hour/opening-hour.module';
import { HamrofoodmanduCategoryModule } from './category/category.module';
import { HamrofoodmanduFoodModule } from './food/food.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HamrofoodmanduResturantModule,
        HamrofoodmanduLocationModule,
        HamrofoodmanduOpeningHourModule,
        HamrofoodmanduCategoryModule,
        HamrofoodmanduFoodModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HamrofoodmanduEntityModule {}
