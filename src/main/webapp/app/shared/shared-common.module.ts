import { NgModule } from '@angular/core';

import { KikkerGatewaySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [KikkerGatewaySharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [KikkerGatewaySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class KikkerGatewaySharedCommonModule {}
