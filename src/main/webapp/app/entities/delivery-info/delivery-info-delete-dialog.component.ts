import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryInfo } from 'app/shared/model/delivery-info.model';
import { DeliveryInfoService } from './delivery-info.service';

@Component({
    selector: 'jhi-delivery-info-delete-dialog',
    templateUrl: './delivery-info-delete-dialog.component.html'
})
export class DeliveryInfoDeleteDialogComponent {
    deliveryInfo: IDeliveryInfo;

    constructor(
        private deliveryInfoService: DeliveryInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deliveryInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'deliveryInfoListModification',
                content: 'Deleted an deliveryInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-delivery-info-delete-popup',
    template: ''
})
export class DeliveryInfoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deliveryInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeliveryInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.deliveryInfo = deliveryInfo;
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
