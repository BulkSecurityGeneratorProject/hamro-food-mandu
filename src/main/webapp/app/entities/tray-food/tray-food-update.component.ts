import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITrayFood } from 'app/shared/model/tray-food.model';
import { TrayFoodService } from './tray-food.service';
import { ITray } from 'app/shared/model/tray.model';
import { TrayService } from 'app/entities/tray';
import { IFood } from 'app/shared/model/food.model';
import { FoodService } from 'app/entities/food';

@Component({
    selector: 'jhi-tray-food-update',
    templateUrl: './tray-food-update.component.html'
})
export class TrayFoodUpdateComponent implements OnInit {
    private _trayFood: ITrayFood;
    isSaving: boolean;

    trays: ITray[];

    foods: IFood[];
    creationDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trayFoodService: TrayFoodService,
        private trayService: TrayService,
        private foodService: FoodService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trayFood }) => {
            this.trayFood = trayFood;
        });
        this.trayService.query().subscribe(
            (res: HttpResponse<ITray[]>) => {
                this.trays = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.foodService.query().subscribe(
            (res: HttpResponse<IFood[]>) => {
                this.foods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trayFood.id !== undefined) {
            this.subscribeToSaveResponse(this.trayFoodService.update(this.trayFood));
        } else {
            this.subscribeToSaveResponse(this.trayFoodService.create(this.trayFood));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrayFood>>) {
        result.subscribe((res: HttpResponse<ITrayFood>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTrayById(index: number, item: ITray) {
        return item.id;
    }

    trackFoodById(index: number, item: IFood) {
        return item.id;
    }
    get trayFood() {
        return this._trayFood;
    }

    set trayFood(trayFood: ITrayFood) {
        this._trayFood = trayFood;
    }
}
