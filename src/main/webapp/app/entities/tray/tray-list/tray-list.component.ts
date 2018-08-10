import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITray } from 'app/shared/model/tray.model';
import { TrayService } from 'app/entities/tray';
import { IFood } from 'app/shared/model/food.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Principal } from 'app/core';
import { TrayFoodService } from 'app/entities/tray-food';
import { ITrayFood } from 'app/shared/model/tray-food.model';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
    selector: 'jhi-tray-list',
    templateUrl: './tray-list.component.html',
    styles: []
})
export class TrayListComponent implements OnInit {
    tray: ITray;
    settingsAccount: any;
    trayFoodList: ITrayFood[];
    total: any;

    constructor(
        private activatedRoute: ActivatedRoute,
        private trayService: TrayService,
        private principal: Principal,
        private trayFoodService: TrayFoodService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            this.trayService.findCurrentUserTray(this.settingsAccount.id).subscribe((res: HttpResponse<ITray>) => {
                this.tray = res.body;
                this.trayFoodService.findByTrayId(this.tray.id).subscribe((res: HttpResponse<ITray[]>) => {
                    this.trayFoodList = res.body;
                    this.calculateTotal();
                });
            });
        });
    }

    previousState() {
        window.history.back();
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

    calculateTotal() {
        this.total = this.trayFoodList.reduce((a, b) => a + b.totalCharge, 0);
    }
}
