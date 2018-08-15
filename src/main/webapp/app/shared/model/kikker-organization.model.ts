export interface IKikker_organization {
    id?: number;
    name?: string;
    zipcode?: string;
    house_nr?: number;
    house_nr_ext?: string;
}

export class Kikker_organization implements IKikker_organization {
    constructor(
        public id?: number,
        public name?: string,
        public zipcode?: string,
        public house_nr?: number,
        public house_nr_ext?: string
    ) {}
}
