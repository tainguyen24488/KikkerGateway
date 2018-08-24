import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-mgmt-update',
    templateUrl: './user-management-update.component.html'
})
export class UserMgmtUpdateComponent implements OnInit {
    user: User;
    languages: any[];
    authorities: any[];
    orgs: any[];
    isSaving: boolean;

    constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {}

    ngOnInit() {
        this.isSaving = false;

        this.authorities = [];
        this.orgs = [];
        this.userService.authorities().subscribe(authorities => {
            this.authorities = authorities;
        });
        // orgs
        this.userService.organization().subscribe(organization => {
            this.orgs = organization;
            this.route.data.subscribe(({ user }) => {
                this.user = user.body ? user.body : user;

                if (!this.user.orgId) {
                    this.user.orgId = this.orgs[0].id;
                }
            });
        });
    }

    previousState() {
        this.router.navigate(['/admin/user-management']);
    }

    save() {
        this.isSaving = true;
        if (this.user.id !== null) {
            this.userService.update(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.user.langKey = 'en';
            this.userService.create(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
