import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IKikker_organization } from 'app/shared/model/kikker-organization.model';
import { Principal } from 'app/core';
import { Kikker_organizationService } from './kikker-organization.service';

@Component({
    selector: 'jhi-kikker-organization',
    templateUrl: './kikker-organization.component.html'
})
export class Kikker_organizationComponent implements OnInit, OnDestroy {
    kikker_organizations: IKikker_organization[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private kikker_organizationService: Kikker_organizationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll(userId: number) {
        this.kikker_organizationService
            .query({
                userId: userId
            })
            .subscribe(
                (res: HttpResponse<IKikker_organization[]>) => {
                    this.kikker_organizations = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadAll(account.id);
        });

        this.registerChangeInKikker_organizations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKikker_organization) {
        return item.id;
    }

    registerChangeInKikker_organizations() {
        this.eventSubscriber = this.eventManager.subscribe('kikker_organizationListModification', response => this.loadAll());
    }

    isAccessed(functionName: string) {
        return this.principal.isAccessed(functionName);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
