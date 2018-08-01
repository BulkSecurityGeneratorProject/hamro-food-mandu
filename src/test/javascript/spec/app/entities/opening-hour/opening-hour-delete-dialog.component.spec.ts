/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { OpeningHourDeleteDialogComponent } from 'app/entities/opening-hour/opening-hour-delete-dialog.component';
import { OpeningHourService } from 'app/entities/opening-hour/opening-hour.service';

describe('Component Tests', () => {
    describe('OpeningHour Management Delete Component', () => {
        let comp: OpeningHourDeleteDialogComponent;
        let fixture: ComponentFixture<OpeningHourDeleteDialogComponent>;
        let service: OpeningHourService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [OpeningHourDeleteDialogComponent]
            })
                .overrideTemplate(OpeningHourDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpeningHourDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpeningHourService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
