import { Moment } from 'moment';

export interface IHFMOrder {
    id?: number;
    status?: string;
    creationDate?: Moment;
    completionDate?: Moment;
    deliveryDate?: Moment;
    totalCharge?: number;
    description?: string;
    trayId?: number;
    paymentName?: string;
    paymentId?: number;
    deliveryInfoId?: number;
    resturantName?: string;
    resturantId?: number;
    userId?: number;
}

export class HFMOrder implements IHFMOrder {
    constructor(
        public id?: number,
        public status?: string,
        public creationDate?: Moment,
        public completionDate?: Moment,
        public deliveryDate?: Moment,
        public totalCharge?: number,
        public description?: string,
        public trayId?: number,
        public paymentName?: string,
        public paymentId?: number,
        public deliveryInfoId?: number,
        public resturantName?: string,
        public resturantId?: number,
        public userId?: number
    ) {}
}
