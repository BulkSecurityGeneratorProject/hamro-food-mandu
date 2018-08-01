export interface ICategory {
    id?: number;
    name?: string;
    image?: string;
}

export class Category implements ICategory {
    constructor(public id?: number, public name?: string, public image?: string) {}
}
