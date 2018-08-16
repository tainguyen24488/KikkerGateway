import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';

@Component({
    selector: 'jhi-kikker-funtion-detail',
    templateUrl: './kikker-funtion-detail.component.html'
})
export class Kikker_funtionDetailComponent implements OnInit {
    kikker_funtion: IKikker_funtion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kikker_funtion }) => {
            this.kikker_funtion = kikker_funtion;
        });
    }

    previousState() {
        window.history.back();
    }
}
