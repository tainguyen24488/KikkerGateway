import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Kikker_organization } from 'app/shared/model/kikker-organization.model';
import { Kikker_organizationService } from './kikker-organization.service';
import { Kikker_organizationComponent } from './kikker-organization.component';
import { Kikker_organizationDetailComponent } from './kikker-organization-detail.component';
import { Kikker_organizationUpdateComponent } from './kikker-organization-update.component';
import { Kikker_organizationDeletePopupComponent } from './kikker-organization-delete-dialog.component';
import { IKikker_organization } from 'app/shared/model/kikker-organization.model';

@Injectable({ providedIn: 'root' })
export class Kikker_organizationResolve implements Resolve<IKikker_organization> {
    constructor(private service: Kikker_organizationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((kikker_organization: HttpResponse<Kikker_organization>) => kikker_organization.body));
        }
        return of(new Kikker_organization());
    }
}

export const kikker_organizationRoute: Routes = [
    {
        path: 'kikker-organization',
        component: Kikker_organizationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Kikker_organizations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-organization/:id/view',
        component: Kikker_organizationDetailComponent,
        resolve: {
            kikker_organization: Kikker_organizationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Kikker_organizations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-organization/new',
        component: Kikker_organizationUpdateComponent,
        resolve: {
            kikker_organization: Kikker_organizationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Kikker_organizations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kikker-organization/:id/edit',
        component: Kikker_organizationUpdateComponent,
        resolve: {
            kikker_organization: Kikker_organizationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Kikker_organizations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kikker_organizationPopupRoute: Routes = [
    {
        path: 'kikker-organization/:id/delete',
        component: Kikker_organizationDeletePopupComponent,
        resolve: {
            kikker_organization: Kikker_organizationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Kikker_organizations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
