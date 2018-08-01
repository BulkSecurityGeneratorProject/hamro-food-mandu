import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOpeningHour } from 'app/shared/model/opening-hour.model';
import { OpeningHourService } from './opening-hour.service';

@Component({
    selector: 'jhi-opening-hour-delete-dialog',
    templateUrl: './opening-hour-delete-dialog.component.html'
})
export class OpeningHourDeleteDialogComponent {
    openingHour: IOpeningHour;

    constructor(
        private openingHourService: OpeningHourService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.openingHourService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'openingHourListModification',
                content: 'Deleted an openingHour'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-opening-hour-delete-popup',
    template: ''
})
export class OpeningHourDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ openingHour }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OpeningHourDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.openingHour = openingHour;
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
