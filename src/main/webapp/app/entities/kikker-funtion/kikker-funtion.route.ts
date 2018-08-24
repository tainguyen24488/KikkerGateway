import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';

import { Principal } from 'app/core';

import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Kikker_funtion } from 'app/shared/model/kikker-funtion.model';
import { Kikker_funtionService } from './kikker-funtion.service';
import { Kikker_funtionComponent } from './kikker-funtion.component';
import { Kikker_funtionDetailComponent } from './kikker-funtion-detail.component';
import { Kikker_funtionUpdateComponent } from './kikker-funtion-update.component';
import { Kikker_funtionDeletePopupComponent } from './kikker-funtion-delete-dialog.component';
import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';

@Injectable({ providedIn: 'root' })
export class Kikker_funtionResolve implements Resolve<IKikker_funtion> {
    constructor(private service: Kikker_funtionService, private principal: Principal) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.principal.identity().then(account => {
            console.log(account);
        });

        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((kikker_funtion: HttpResponse<Kikker_funtion>) => kikker_funtion.body));
        }
        return of(new Kikker_funtion());
    }
}

export const kikker_funtionRoute: Routes = [
    {
        path: 'kikker-funtion',
        component: Kikker_funtionComponent,
        data: {
            authorities: ['BO_RESELLER', 'ROLE_ADMIN'],
            pageTitle: 'Kikker_funtions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-funtion/:id/view',
        component: Kikker_funtionDetailComponent,
        resolve: {
            kikker_funtion: Kikker_funtionResolve
        },
        data: {
            authorities: ['BO_RESELLER', 'ROLE_ADMIN'],
            pageTitle: 'Kikker_funtions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-funtion/new',
        component: Kikker_funtionUpdateComponent,
        resolve: {
            kikker_funtion: Kikker_funtionResolve
        },
        data: {
            authorities: ['BO_RESELLER', 'ROLE_ADMIN'],
            pageTitle: 'Kikker_funtions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-funtion/:id/edit',
        component: Kikker_funtionUpdateComponent,
        resolve: {
            kikker_funtion: Kikker_funtionResolve
        },
        data: {
            authorities: ['BO_RESELLER', 'ROLE_ADMIN'],
            pageTitle: 'Kikker_funtions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kikker_funtionPopupRoute: Routes = [
    {
        path: 'kikker-funtion/:id/delete',
        component: Kikker_funtionDeletePopupComponent,
        resolve: {
            kikker_funtion: Kikker_funtionResolve
        },
        data: {
            authorities: ['BO_RESELLER', 'ROLE_ADMIN'],
            pageTitle: 'Kikker_funtions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
