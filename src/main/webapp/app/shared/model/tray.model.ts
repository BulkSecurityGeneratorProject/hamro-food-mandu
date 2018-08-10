import { Moment } from 'moment';

export interface ITray {
    id?: number;
    status?: string;
    creationDate?: Moment;
    extra?: string;
    userId?: number;
}

export class Tray implements ITray {
    constructor(public id?: number, public status?: string, public creationDate?: Moment, public extra?: string, public userId?: number) {}
}
