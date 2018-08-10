import { Component, OnInit } from '@angular/core';
import { ITray } from 'app/shared/model/tray.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Principal } from 'app/core';
import { HFMOrderService } from 'app/entities/hfm-order';
import { IHFMOrder } from 'app/shared/model/hfm-order.model';

@Component({
    selector: 'jhi-hm-order-list',
    templateUrl: './hm-order-list.component.html',
    styles: []
})
export class HFMOrderListComponent implements OnInit {
    settingsAccount: any;
    hFMOrders: IHFMOrder[];

    constructor(private principal: Principal, private hFMOrderService: HFMOrderService) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            this.hFMOrderService
                .findByUserId(this.settingsAccount.id)
                .subscribe((res: HttpResponse<IHFMOrder[]>) => (this.hFMOrders = res.body));
        });
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
    previousState() {
        window.history.back();
    }
}
