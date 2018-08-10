import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITray } from 'app/shared/model/tray.model';
import { TrayService } from './tray.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-tray-update',
    templateUrl: './tray-update.component.html'
})
export class TrayUpdateComponent implements OnInit {
    private _tray: ITray;
    isSaving: boolean;

    users: IUser[];
    creationDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trayService: TrayService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tray }) => {
            this.tray = tray;
        });
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
        if (this.tray.id !== undefined) {
            this.subscribeToSaveResponse(this.trayService.update(this.tray));
        } else {
            this.subscribeToSaveResponse(this.trayService.create(this.tray));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITray>>) {
        result.subscribe((res: HttpResponse<ITray>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get tray() {
        return this._tray;
    }

    set tray(tray: ITray) {
        this._tray = tray;
    }
}
