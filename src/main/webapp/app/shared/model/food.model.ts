export interface IFood {
    id?: number;
    name?: string;
    image?: string;
    unitPrice?: number;
    description?: string;
    categoryName?: string;
    categoryId?: number;
    resturantName?: string;
    resturantId?: number;
}

export class Food implements IFood {
    constructor(
        public id?: number,
        public name?: string,
        public image?: string,
        public unitPrice?: number,
        public description?: string,
        public categoryName?: string,
        public categoryId?: number,
        public resturantName?: string,
        public resturantId?: number
    ) {}
}
