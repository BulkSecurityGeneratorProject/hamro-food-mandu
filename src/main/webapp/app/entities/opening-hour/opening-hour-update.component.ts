import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOpeningHour } from 'app/shared/model/opening-hour.model';
import { OpeningHourService } from './opening-hour.service';

@Component({
    selector: 'jhi-opening-hour-update',
    templateUrl: './opening-hour-update.component.html'
})
export class OpeningHourUpdateComponent implements OnInit {
    private _openingHour: IOpeningHour;
    isSaving: boolean;

    constructor(private openingHourService: OpeningHourService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ openingHour }) => {
            this.openingHour = openingHour;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.openingHour.id !== undefined) {
            this.subscribeToSaveResponse(this.openingHourService.update(this.openingHour));
        } else {
            this.subscribeToSaveResponse(this.openingHourService.create(this.openingHour));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOpeningHour>>) {
        result.subscribe((res: HttpResponse<IOpeningHour>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get openingHour() {
        return this._openingHour;
    }

    set openingHour(openingHour: IOpeningHour) {
        this._openingHour = openingHour;
    }
}
