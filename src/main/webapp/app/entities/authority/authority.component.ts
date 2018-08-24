import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAuthority } from 'app/shared/model/authority.model';
import { Principal } from 'app/core';
import { AuthorityService } from './authority.service';

@Component({
    selector: 'jhi-authority',
    templateUrl: './authority.component.html'
})
export class AuthorityComponent implements OnInit, OnDestroy {
    authorities: IAuthority[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private authorityService: AuthorityService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.authorityService.query().subscribe(
            (res: HttpResponse<IAuthority[]>) => {
                this.authorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAuthorities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAuthority) {
        return item.id;
    }

    registerChangeInAuthorities() {
        this.eventSubscriber = this.eventManager.subscribe('authorityListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
