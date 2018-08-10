export interface IDeliveryInfo {
    id?: number;
    firstName?: string;
    lastName?: string;
    phoneNo?: string;
    address?: string;
    description?: string;
}

export class DeliveryInfo implements IDeliveryInfo {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public phoneNo?: string,
        public address?: string,
        public description?: string
    ) {}
}
