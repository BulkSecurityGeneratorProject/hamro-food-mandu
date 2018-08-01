export interface ILocation {
    id?: number;
    name?: string;
    latitude?: string;
    longitude?: string;
    image?: string;
}

export class Location implements ILocation {
    constructor(public id?: number, public name?: string, public latitude?: string, public longitude?: string, public image?: string) {}
}
