/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { KikkerGatewayTestModule } from '../../../test.module';
import { Kikker_organizationComponent } from 'app/entities/kikker-organization/kikker-organization.component';
import { Kikker_organizationService } from 'app/entities/kikker-organization/kikker-organization.service';
import { Kikker_organization } from 'app/shared/model/kikker-organization.model';

describe('Component Tests', () => {
    describe('Kikker_organization Management Component', () => {
        let comp: Kikker_organizationComponent;
        let fixture: ComponentFixture<Kikker_organizationComponent>;
        let service: Kikker_organizationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KikkerGatewayTestModule],
                declarations: [Kikker_organizationComponent],
                providers: []
            })
                .overrideTemplate(Kikker_organizationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Kikker_organizationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Kikker_organizationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Kikker_organization(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.kikker_organizations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
