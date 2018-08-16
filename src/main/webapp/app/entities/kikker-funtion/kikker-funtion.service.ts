import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKikker_funtion } from 'app/shared/model/kikker-funtion.model';

type EntityResponseType = HttpResponse<IKikker_funtion>;
type EntityArrayResponseType = HttpResponse<IKikker_funtion[]>;

@Injectable({ providedIn: 'root' })
export class Kikker_funtionService {
    private resourceUrl = SERVER_API_URL + 'api/kikker-funtions';

    constructor(private http: HttpClient) {}

    create(kikker_funtion: IKikker_funtion): Observable<EntityResponseType> {
        return this.http.post<IKikker_funtion>(this.resourceUrl, kikker_funtion, { observe: 'response' });
    }

    update(kikker_funtion: IKikker_funtion): Observable<EntityResponseType> {
        return this.http.put<IKikker_funtion>(this.resourceUrl, kikker_funtion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKikker_funtion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKikker_funtion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
