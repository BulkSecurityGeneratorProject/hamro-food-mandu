import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHFMOrder } from 'app/shared/model/hfm-order.model';

@Component({
    selector: 'jhi-hfm-order-detail',
    templateUrl: './hfm-order-detail.component.html'
})
export class HFMOrderDetailComponent implements OnInit {
    hFMOrder: IHFMOrder;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hFMOrder }) => {
            this.hFMOrder = hFMOrder;
        });
    }

    previousState() {
        window.history.back();
    }
}
