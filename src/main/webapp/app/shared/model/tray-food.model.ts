import { Moment } from 'moment';

export interface ITrayFood {
    id?: number;
    quantity?: number;
    totalCharge?: number;
    creationDate?: Moment;
    extra?: string;
    trayId?: number;
    foodName?: string;
    foodId?: number;
}

export class TrayFood implements ITrayFood {
    constructor(
        public id?: number,
        public quantity?: number,
        public totalCharge?: number,
        public creationDate?: Moment,
        public extra?: string,
        public trayId?: number,
        public foodName?: string,
        public foodId?: number
    ) {}
}
