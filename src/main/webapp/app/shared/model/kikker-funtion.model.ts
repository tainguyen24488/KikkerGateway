export interface IKikker_funtion {
    id?: number;
    name?: string;
    actived?: boolean;
}

export class Kikker_funtion implements IKikker_funtion {
    constructor(public id?: number, public name?: string, public actived?: boolean) {
        this.actived = false;
    }
}
