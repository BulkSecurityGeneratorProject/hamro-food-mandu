/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { OpeningHourUpdateComponent } from 'app/entities/opening-hour/opening-hour-update.component';
import { OpeningHourService } from 'app/entities/opening-hour/opening-hour.service';
import { OpeningHour } from 'app/shared/model/opening-hour.model';

describe('Component Tests', () => {
    describe('OpeningHour Management Update Component', () => {
        let comp: OpeningHourUpdateComponent;
        let fixture: ComponentFixture<OpeningHourUpdateComponent>;
        let service: OpeningHourService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [OpeningHourUpdateComponent]
            })
                .overrideTemplate(OpeningHourUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OpeningHourUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OpeningHourService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OpeningHour(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.openingHour = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OpeningHour();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.openingHour = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
