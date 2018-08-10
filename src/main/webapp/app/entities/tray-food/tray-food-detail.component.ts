import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrayFood } from 'app/shared/model/tray-food.model';

@Component({
    selector: 'jhi-tray-food-detail',
    templateUrl: './tray-food-detail.component.html'
})
export class TrayFoodDetailComponent implements OnInit {
    trayFood: ITrayFood;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trayFood }) => {
            this.trayFood = trayFood;
        });
    }

    previousState() {
        window.history.back();
    }
}
