import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IHFMOrder } from 'app/shared/model/hfm-order.model';
import { HFMOrderService } from './hfm-order.service';
import { ITray } from 'app/shared/model/tray.model';
import { TrayService } from 'app/entities/tray';
import { IPaymentType } from 'app/shared/model/payment-type.model';
import { PaymentTypeService } from 'app/entities/payment-type';
import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';
import { DeliveryInfoService } from 'app/entities/delivery-info';
import { IResturant } from 'app/shared/model/resturant.model';
import { ResturantService } from 'app/entities/resturant';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-hfm-order-update',
    templateUrl: './hfm-order-update.component.html'
})
export class HFMOrderUpdateComponent implements OnInit {
    private _hFMOrder: IHFMOrder;
    isSaving: boolean;

    trays: ITray[];

    paymenttypes: IPaymentType[];

    deliveryinfos: IDeliveryInfo[];

    resturants: IResturant[];

    users: IUser[];
    creationDateDp: any;
    completionDateDp: any;
    deliveryDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private hFMOrderService: HFMOrderService,
        private trayService: TrayService,
        private paymentTypeService: PaymentTypeService,
        private deliveryInfoService: DeliveryInfoService,
        private resturantService: ResturantService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hFMOrder }) => {
            this.hFMOrder = hFMOrder;
        });
        this.trayService.query({ filter: 'hfmorder-is-null' }).subscribe(
            (res: HttpResponse<ITray[]>) => {
                if (!this.hFMOrder.trayId) {
                    this.trays = res.body;
                } else {
                    this.trayService.find(this.hFMOrder.trayId).subscribe(
                        (subRes: HttpResponse<ITray>) => {
                            this.trays = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paymentTypeService.query().subscribe(
            (res: HttpResponse<IPaymentType[]>) => {
                this.paymenttypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.deliveryInfoService.query().subscribe(
            (res: HttpResponse<IDeliveryInfo[]>) => {
                this.deliveryinfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resturantService.query().subscribe(
            (res: HttpResponse<IResturant[]>) => {
                this.resturants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.hFMOrder.id !== undefined) {
            this.subscribeToSaveResponse(this.hFMOrderService.update(this.hFMOrder));
        } else {
            this.subscribeToSaveResponse(this.hFMOrderService.create(this.hFMOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHFMOrder>>) {
        result.subscribe((res: HttpResponse<IHFMOrder>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPaymentTypeById(index: number, item: IPaymentType) {
        return item.id;
    }

    trackDeliveryInfoById(index: number, item: IDeliveryInfo) {
        return item.id;
    }

    trackResturantById(index: number, item: IResturant) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get hFMOrder() {
        return this._hFMOrder;
    }

    set hFMOrder(hFMOrder: IHFMOrder) {
        this._hFMOrder = hFMOrder;
    }
}
