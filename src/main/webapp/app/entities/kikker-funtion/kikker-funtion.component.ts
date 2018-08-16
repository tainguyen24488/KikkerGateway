import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';
import { Principal } from 'app/core';
import { Kikker_funtionService } from './kikker-funtion.service';

@Component({
    selector: 'jhi-kikker-funtion',
    templateUrl: './kikker-funtion.component.html'
})
export class Kikker_funtionComponent implements OnInit, OnDestroy {
    kikker_funtions: IKikker_funtion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private kikker_funtionService: Kikker_funtionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.kikker_funtionService.query().subscribe(
            (res: HttpResponse<IKikker_funtion[]>) => {
                this.kikker_funtions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInKikker_funtions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKikker_funtion) {
        return item.id;
    }

    registerChangeInKikker_funtions() {
        this.eventSubscriber = this.eventManager.subscribe('kikker_funtionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
