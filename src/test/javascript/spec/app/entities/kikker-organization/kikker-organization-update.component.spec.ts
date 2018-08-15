/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KikkerGatewayTestModule } from '../../../test.module';
import { Kikker_organizationUpdateComponent } from 'app/entities/kikker-organization/kikker-organization-update.component';
import { Kikker_organizationService } from 'app/entities/kikker-organization/kikker-organization.service';
import { Kikker_organization } from 'app/shared/model/kikker-organization.model';

describe('Component Tests', () => {
    describe('Kikker_organization Management Update Component', () => {
        let comp: Kikker_organizationUpdateComponent;
        let fixture: ComponentFixture<Kikker_organizationUpdateComponent>;
        let service: Kikker_organizationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KikkerGatewayTestModule],
                declarations: [Kikker_organizationUpdateComponent]
            })
                .overrideTemplate(Kikker_organizationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Kikker_organizationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Kikker_organizationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Kikker_organization(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kikker_organization = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Kikker_organization();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.kikker_organization = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
