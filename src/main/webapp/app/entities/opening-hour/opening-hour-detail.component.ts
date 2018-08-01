import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOpeningHour } from 'app/shared/model/opening-hour.model';

@Component({
    selector: 'jhi-opening-hour-detail',
    templateUrl: './opening-hour-detail.component.html'
})
export class OpeningHourDetailComponent implements OnInit {
    openingHour: IOpeningHour;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ openingHour }) => {
            this.openingHour = openingHour;
        });
    }

    previousState() {
        window.history.back();
    }
}
