/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { ResturantDeleteDialogComponent } from 'app/entities/resturant/resturant-delete-dialog.component';
import { ResturantService } from 'app/entities/resturant/resturant.service';

describe('Component Tests', () => {
    describe('Resturant Management Delete Component', () => {
        let comp: ResturantDeleteDialogComponent;
        let fixture: ComponentFixture<ResturantDeleteDialogComponent>;
        let service: ResturantService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [ResturantDeleteDialogComponent]
            })
                .overrideTemplate(ResturantDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResturantDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResturantService);
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
