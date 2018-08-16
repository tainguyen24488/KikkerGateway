import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { KikkerGatewaySharedModule } from 'app/shared';
import {
    Kikker_funtionComponent,
    Kikker_funtionDetailComponent,
    Kikker_funtionUpdateComponent,
    Kikker_funtionDeletePopupComponent,
    Kikker_funtionDeleteDialogComponent,
    kikker_funtionRoute,
    kikker_funtionPopupRoute
} from './';

const ENTITY_STATES = [...kikker_funtionRoute, ...kikker_funtionPopupRoute];

@NgModule({
    imports: [KikkerGatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        Kikker_funtionComponent,
        Kikker_funtionDetailComponent,
        Kikker_funtionUpdateComponent,
        Kikker_funtionDeleteDialogComponent,
        Kikker_funtionDeletePopupComponent
    ],
    entryComponents: [
        Kikker_funtionComponent,
        Kikker_funtionUpdateComponent,
        Kikker_funtionDeleteDialogComponent,
        Kikker_funtionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KikkerGatewayKikker_funtionModule {}
