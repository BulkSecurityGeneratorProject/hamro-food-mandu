export interface IOpeningHour {
    id?: number;
    day?: string;
    time?: string;
}

export class OpeningHour implements IOpeningHour {
    constructor(public id?: number, public day?: string, public time?: string) {}
}
