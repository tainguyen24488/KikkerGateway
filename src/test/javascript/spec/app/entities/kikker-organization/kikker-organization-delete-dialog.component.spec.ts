/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KikkerGatewayTestModule } from '../../../test.module';
import { Kikker_organizationDeleteDialogComponent } from 'app/entities/kikker-organization/kikker-organization-delete-dialog.component';
import { Kikker_organizationService } from 'app/entities/kikker-organization/kikker-organization.service';

describe('Component Tests', () => {
    describe('Kikker_organization Management Delete Component', () => {
        let comp: Kikker_organizationDeleteDialogComponent;
        let fixture: ComponentFixture<Kikker_organizationDeleteDialogComponent>;
        let service: Kikker_organizationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KikkerGatewayTestModule],
                declarations: [Kikker_organizationDeleteDialogComponent]
            })
                .overrideTemplate(Kikker_organizationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Kikker_organizationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Kikker_organizationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
