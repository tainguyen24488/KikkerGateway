import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKikker_organization } from 'app/shared/model/kikker-organization.model';

type EntityResponseType = HttpResponse<IKikker_organization>;
type EntityArrayResponseType = HttpResponse<IKikker_organization[]>;

@Injectable({ providedIn: 'root' })
export class Kikker_organizationService {
    private resourceUrl = SERVER_API_URL + 'api/kikker-organizations';

    constructor(private http: HttpClient) {}

    create(kikker_organization: IKikker_organization): Observable<EntityResponseType> {
        return this.http.post<IKikker_organization>(this.resourceUrl, kikker_organization, { observe: 'response' });
    }

    update(kikker_organization: IKikker_organization): Observable<EntityResponseType> {
        return this.http.put<IKikker_organization>(this.resourceUrl, kikker_organization, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKikker_organization>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKikker_organization[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
