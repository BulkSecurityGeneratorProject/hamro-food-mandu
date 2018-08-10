import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';

@Component({
    selector: 'jhi-delivery-info-detail',
    templateUrl: './delivery-info-detail.component.html'
})
export class DeliveryInfoDetailComponent implements OnInit {
    deliveryInfo: IDeliveryInfo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deliveryInfo }) => {
            this.deliveryInfo = deliveryInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
