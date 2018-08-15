/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KikkerGatewayTestModule } from '../../../test.module';
import { Kikker_organizationDetailComponent } from 'app/entities/kikker-organization/kikker-organization-detail.component';
import { Kikker_organization } from 'app/shared/model/kikker-organization.model';

describe('Component Tests', () => {
    describe('Kikker_organization Management Detail Component', () => {
        let comp: Kikker_organizationDetailComponent;
        let fixture: ComponentFixture<Kikker_organizationDetailComponent>;
        const route = ({ data: of({ kikker_organization: new Kikker_organization(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KikkerGatewayTestModule],
                declarations: [Kikker_organizationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(Kikker_organizationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Kikker_organizationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.kikker_organization).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
