import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITray } from 'app/shared/model/tray.model';

@Component({
    selector: 'jhi-tray-detail',
    templateUrl: './tray-detail.component.html'
})
export class TrayDetailComponent implements OnInit {
    tray: ITray;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tray }) => {
            this.tray = tray;
        });
    }

    previousState() {
        window.history.back();
    }
}
