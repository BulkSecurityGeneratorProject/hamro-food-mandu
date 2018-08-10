import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrayFood } from 'app/shared/model/tray-food.model';
import { TrayFoodService } from './tray-food.service';

@Component({
    selector: 'jhi-tray-food-delete-dialog',
    templateUrl: './tray-food-delete-dialog.component.html'
})
export class TrayFoodDeleteDialogComponent {
    trayFood: ITrayFood;

    constructor(private trayFoodService: TrayFoodService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.trayFoodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'trayFoodListModification',
                content: 'Deleted an trayFood'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tray-food-delete-popup',
    template: ''
})
export class TrayFoodDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trayFood }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TrayFoodDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.trayFood = trayFood;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
