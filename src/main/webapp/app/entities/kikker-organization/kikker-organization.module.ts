import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KikkerGatewaySharedModule } from 'app/shared';
import {
    Kikker_organizationComponent,
    Kikker_organizationDetailComponent,
    Kikker_organizationUpdateComponent,
    Kikker_organizationDeletePopupComponent,
    Kikker_organizationDeleteDialogComponent,
    kikker_organizationRoute,
    kikker_organizationPopupRoute
} from './';

const ENTITY_STATES = [...kikker_organizationRoute, ...kikker_organizationPopupRoute];

@NgModule({
    imports: [KikkerGatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        Kikker_organizationComponent,
        Kikker_organizationDetailComponent,
        Kikker_organizationUpdateComponent,
        Kikker_organizationDeleteDialogComponent,
        Kikker_organizationDeletePopupComponent
    ],
    entryComponents: [
        Kikker_organizationComponent,
        Kikker_organizationUpdateComponent,
        Kikker_organizationDeleteDialogComponent,
        Kikker_organizationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KikkerGatewayKikker_organizationModule {}
