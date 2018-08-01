/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { ResturantUpdateComponent } from 'app/entities/resturant/resturant-update.component';
import { ResturantService } from 'app/entities/resturant/resturant.service';
import { Resturant } from 'app/shared/model/resturant.model';

describe('Component Tests', () => {
    describe('Resturant Management Update Component', () => {
        let comp: ResturantUpdateComponent;
        let fixture: ComponentFixture<ResturantUpdateComponent>;
        let service: ResturantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [ResturantUpdateComponent]
            })
                .overrideTemplate(ResturantUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ResturantUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResturantService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Resturant(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resturant = entity;
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
                    const entity = new Resturant();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resturant = entity;
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
