import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResturant } from 'app/shared/model/resturant.model';

@Component({
    selector: 'jhi-resturant-detail',
    templateUrl: './resturant-detail.component.html'
})
export class ResturantDetailComponent implements OnInit {
    resturant: IResturant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resturant }) => {
            this.resturant = resturant;
        });
    }

    previousState() {
        window.history.back();
    }
}
