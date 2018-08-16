import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';
import { Kikker_funtionService } from './kikker-funtion.service';

@Component({
    selector: 'jhi-kikker-funtion-delete-dialog',
    templateUrl: './kikker-funtion-delete-dialog.component.html'
})
export class Kikker_funtionDeleteDialogComponent {
    kikker_funtion: IKikker_funtion;

    constructor(
        private kikker_funtionService: Kikker_funtionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kikker_funtionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kikker_funtionListModification',
                content: 'Deleted an kikker_funtion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kikker-funtion-delete-popup',
    template: ''
})
export class Kikker_funtionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kikker_funtion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(Kikker_funtionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.kikker_funtion = kikker_funtion;
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
