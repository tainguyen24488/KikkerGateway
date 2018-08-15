import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKikker_organization } from 'app/shared/model/kikker-organization.model';
import { Kikker_organizationService } from './kikker-organization.service';

@Component({
    selector: 'jhi-kikker-organization-update',
    templateUrl: './kikker-organization-update.component.html'
})
export class Kikker_organizationUpdateComponent implements OnInit {
    private _kikker_organization: IKikker_organization;
    isSaving: boolean;

    constructor(private kikker_organizationService: Kikker_organizationService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kikker_organization }) => {
            this.kikker_organization = kikker_organization;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kikker_organization.id !== undefined) {
            this.subscribeToSaveResponse(this.kikker_organizationService.update(this.kikker_organization));
        } else {
            this.subscribeToSaveResponse(this.kikker_organizationService.create(this.kikker_organization));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKikker_organization>>) {
        result.subscribe((res: HttpResponse<IKikker_organization>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get kikker_organization() {
        return this._kikker_organization;
    }

    set kikker_organization(kikker_organization: IKikker_organization) {
        this._kikker_organization = kikker_organization;
    }
}
