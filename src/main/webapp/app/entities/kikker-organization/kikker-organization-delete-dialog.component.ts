import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKikker_organization } from 'app/shared/model/kikker-organization.model';
import { Kikker_organizationService } from './kikker-organization.service';

@Component({
    selector: 'jhi-kikker-organization-delete-dialog',
    templateUrl: './kikker-organization-delete-dialog.component.html'
})
export class Kikker_organizationDeleteDialogComponent {
    kikker_organization: IKikker_organization;

    constructor(
        private kikker_organizationService: Kikker_organizationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kikker_organizationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kikker_organizationListModification',
                content: 'Deleted an kikker_organization'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kikker-organization-delete-popup',
    template: ''
})
export class Kikker_organizationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kikker_organization }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(Kikker_organizationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.kikker_organization = kikker_organization;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
