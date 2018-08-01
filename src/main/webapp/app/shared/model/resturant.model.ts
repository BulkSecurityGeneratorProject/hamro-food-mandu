import { IOpeningHour } from 'app/shared/model//opening-hour.model';

export interface IResturant {
    id?: number;
    name?: string;
    serviceCharge?: number;
    vat?: number;
    description?: string;
    locationName?: string;
    locationId?: number;
    openingHours?: IOpeningHour[];
}

export class Resturant implements IResturant {
    constructor(
        public id?: number,
        public name?: string,
        public serviceCharge?: number,
        public vat?: number,
        public description?: string,
        public locationName?: string,
        public locationId?: number,
        public openingHours?: IOpeningHour[]
    ) {}
}
