import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';
import { Kikker_funtionService } from './kikker-funtion.service';

@Component({
    selector: 'jhi-kikker-funtion-update',
    templateUrl: './kikker-funtion-update.component.html'
})
export class Kikker_funtionUpdateComponent implements OnInit {
    private _kikker_funtion: IKikker_funtion;
    isSaving: boolean;

    constructor(private kikker_funtionService: Kikker_funtionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kikker_funtion }) => {
            this.kikker_funtion = kikker_funtion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kikker_funtion.id !== undefined) {
            this.subscribeToSaveResponse(this.kikker_funtionService.update(this.kikker_funtion));
        } else {
            this.subscribeToSaveResponse(this.kikker_funtionService.create(this.kikker_funtion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKikker_funtion>>) {
        result.subscribe((res: HttpResponse<IKikker_funtion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get kikker_funtion() {
        return this._kikker_funtion;
    }

    set kikker_funtion(kikker_funtion: IKikker_funtion) {
        this._kikker_funtion = kikker_funtion;
    }
}
