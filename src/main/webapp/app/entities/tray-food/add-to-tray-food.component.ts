import { Component, OnInit } from '@angular/core';
import { IFood } from 'app/shared/model/food.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ITrayFood } from 'app/shared/model/tray-food.model';
import { TrayFoodService } from 'app/entities/tray-food/tray-food.service';
import { TrayService } from 'app/entities/tray';
import { FoodService } from 'app/entities/food';
import { ActivatedRoute } from '@angular/router';
import { ITray } from 'app/shared/model/tray.model';
import { JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs/index';
import moment = require('moment');
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-add-to-tray-food',
    templateUrl: './add-to-tray-food.component.html',
    styles: []
})
export class AddToTrayFoodComponent implements OnInit {
    private _trayFood: ITrayFood = {};
    isSaving: boolean;
    tray: ITray;
    food: IFood;
    foodId: any;
    settingsAccount: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trayFoodService: TrayFoodService,
        private trayService: TrayService,
        private foodService: FoodService,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {}

    ngOnInit() {
        this._trayFood.quantity = 1;
        this.isSaving = false;
        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            this.trayService.findCurrentUserTray(this.settingsAccount.id).subscribe(
                (res: HttpResponse<ITray>) => {
                    this.tray = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });

        this.activatedRoute.params.subscribe(params => {
            this.foodId = +params['id'];
            this.foodService.find(this.foodId).subscribe(
                (res: HttpResponse<IFood>) => {
                    this.food = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        console.log(this.trayFood);
        this.trayFood.totalCharge = this.food.unitPrice * this.trayFood.quantity;
        this.trayFood.creationDate = moment();
        this.trayFood.foodId = this.food.id;
        this.trayFood.foodName = this.food.name;

        //create new tray if doesn't exist
        if (!this.tray) {
            this.tray = {};
            this.tray.creationDate = moment();
            this.tray.status = 'opened';
            this.tray.userId = this.settingsAccount.id;
            this.tray.extra = '';
            this.trayService.create(this.tray).subscribe(
                (res: HttpResponse<ITray>) => {
                    // this.onSaveSuccess();
                    this.tray = res.body;
                    console.log('tray created');
                    this.isSaving = true;
                    this.trayFood.trayId = this.tray.id;
                    this.subscribeToSaveResponse(this.trayFoodService.create(this.trayFood));
                    console.log('food added on tray');
                },
                (res: HttpErrorResponse) => this.onSaveError()
            );
        } else {
            //use current existing tray
            console.log('using existing array');
            this.isSaving = true;
            this.trayFood.trayId = this.tray.id;
            this.subscribeToSaveResponse(this.trayFoodService.create(this.trayFood));
            console.log('food added on tray');
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

    copyAccount(account) {
        return {
            id: account.id,
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl
        };
    }
}
