import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHFMOrder } from 'app/shared/model/hfm-order.model';
import { HFMOrderService } from './hfm-order.service';

@Component({
    selector: 'jhi-hfm-order-delete-dialog',
    templateUrl: './hfm-order-delete-dialog.component.html'
})
export class HFMOrderDeleteDialogComponent {
    hFMOrder: IHFMOrder;

    constructor(private hFMOrderService: HFMOrderService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.hFMOrderService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'hFMOrderListModification',
                content: 'Deleted an hFMOrder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-hfm-order-delete-popup',
    template: ''
})
export class HFMOrderDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hFMOrder }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HFMOrderDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.hFMOrder = hFMOrder;
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
