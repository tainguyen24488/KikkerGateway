import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { KikkerGatewayKikker_organizationModule } from './kikker-organization/kikker-organization.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        KikkerGatewayKikker_organizationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KikkerGatewayEntityModule {}
