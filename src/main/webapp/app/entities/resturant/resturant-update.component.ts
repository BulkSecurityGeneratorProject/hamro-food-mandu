import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IResturant } from 'app/shared/model/resturant.model';
import { ResturantService } from './resturant.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location';
import { IOpeningHour } from 'app/shared/model/opening-hour.model';
import { OpeningHourService } from 'app/entities/opening-hour';

@Component({
    selector: 'jhi-resturant-update',
    templateUrl: './resturant-update.component.html'
})
export class ResturantUpdateComponent implements OnInit {
    private _resturant: IResturant;
    isSaving: boolean;

    locations: ILocation[];

    openinghours: IOpeningHour[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private resturantService: ResturantService,
        private locationService: LocationService,
        private openingHourService: OpeningHourService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resturant }) => {
            this.resturant = resturant;
        });
        this.locationService.query({ filter: 'resturant-is-null' }).subscribe(
            (res: HttpResponse<ILocation[]>) => {
                if (!this.resturant.locationId) {
                    this.locations = res.body;
                } else {
                    this.locationService.find(this.resturant.locationId).subscribe(
                        (subRes: HttpResponse<ILocation>) => {
                            this.locations = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.openingHourService.query().subscribe(
            (res: HttpResponse<IOpeningHour[]>) => {
                this.openinghours = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resturant.id !== undefined) {
            this.subscribeToSaveResponse(this.resturantService.update(this.resturant));
        } else {
            this.subscribeToSaveResponse(this.resturantService.create(this.resturant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResturant>>) {
        result.subscribe((res: HttpResponse<IResturant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLocationById(index: number, item: ILocation) {
        return item.id;
    }

    trackOpeningHourById(index: number, item: IOpeningHour) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get resturant() {
        return this._resturant;
    }

    set resturant(resturant: IResturant) {
        this._resturant = resturant;
    }
}
