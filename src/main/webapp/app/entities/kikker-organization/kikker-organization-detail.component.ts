import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKikker_organization } from 'app/shared/model/kikker-organization.model';

@Component({
    selector: 'jhi-kikker-organization-detail',
    templateUrl: './kikker-organization-detail.component.html'
})
export class Kikker_organizationDetailComponent implements OnInit {
    kikker_organization: IKikker_organization;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kikker_organization }) => {
            this.kikker_organization = kikker_organization;
        });
    }

    previousState() {
        window.history.back();
    }
}
