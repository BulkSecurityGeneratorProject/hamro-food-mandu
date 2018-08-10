import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';
import { DeliveryInfoService } from './delivery-info.service';
import { HFMOrder, IHFMOrder } from 'app/shared/model/hfm-order.model';
import { IPaymentType } from 'app/shared/model/payment-type.model';
import { PaymentTypeService } from 'app/entities/payment-type';
import { ITray, Tray } from 'app/shared/model/tray.model';
import { Principal } from 'app/core';
import { TrayService } from 'app/entities/tray';
import { TrayFoodService } from 'app/entities/tray-food';
import { ITrayFood } from 'app/shared/model/tray-food.model';
import moment = require('moment');
import { HFMOrderService } from 'app/entities/hfm-order';

@Component({
    selector: 'jhi-delivery-info-update',
    templateUrl: './delivery-info-update.component.html'
})
export class DeliveryInfoUpdateComponent implements OnInit {
    private _deliveryInfo: IDeliveryInfo;
    private _hFMOrder: IHFMOrder = {};
    paymenttypes: IPaymentType[];
    tray: ITray;
    settingsAccount: any;
    trayFoodList: ITrayFood[];
    total: any;
    isSaving: boolean;

    constructor(
        private deliveryInfoService: DeliveryInfoService,
        private activatedRoute: ActivatedRoute,
        private paymentTypeService: PaymentTypeService,
        private principal: Principal,
        private trayService: TrayService,
        private trayFoodService: TrayFoodService,
        private hFMOrderService: HFMOrderService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deliveryInfo }) => {
            this.deliveryInfo = deliveryInfo;
        });

        this.paymentTypeService.query().subscribe((res: HttpResponse<IPaymentType[]>) => {
            this.paymenttypes = res.body;
        });

        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            this.trayService.findCurrentUserTray(this.settingsAccount.id).subscribe((res: HttpResponse<ITray>) => {
                this.tray = res.body;
                this.trayFoodService.findByTrayId(this.tray.id).subscribe((res: HttpResponse<ITray[]>) => {
                    this.trayFoodList = res.body;
                });
            });
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        //updating
        if (this.deliveryInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.deliveryInfoService.update(this.deliveryInfo));
        } else {
            //creating  new order
            this.deliveryInfoService.create(this.deliveryInfo).subscribe((res: HttpResponse<IDeliveryInfo>) => {
                this.deliveryInfo = res.body;
                this._hFMOrder.userId = this.settingsAccount.id;
                this._hFMOrder.totalCharge = this.trayFoodList.reduce((a, b) => a + b.totalCharge, 0);
                this._hFMOrder.status = 'PENDING';
                this._hFMOrder.trayId = this.tray.id;
                this._hFMOrder.userId = this.tray.userId;
                this._hFMOrder.creationDate = moment();
                this._hFMOrder.deliveryInfoId = this.deliveryInfo.id;
                this.hFMOrderService.create(this._hFMOrder).subscribe((res: HttpResponse<HFMOrder>) => {
                    this.tray.status = 'closed';
                    this.trayService.update(this.tray).subscribe((res: HttpResponse<Tray>) => this.onSaveSuccess());
                });
            });
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryInfo>>) {
        result.subscribe((res: HttpResponse<IDeliveryInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    get deliveryInfo() {
        return this._deliveryInfo;
    }

    set deliveryInfo(deliveryInfo: IDeliveryInfo) {
        this._deliveryInfo = deliveryInfo;
    }

    copyAccount(account) {
        return {
            activated: account.activated,
            id: account.id,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl
        };
    }
}
