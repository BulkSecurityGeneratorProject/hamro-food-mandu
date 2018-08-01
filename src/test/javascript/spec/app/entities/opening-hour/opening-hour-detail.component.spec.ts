/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { OpeningHourDetailComponent } from 'app/entities/opening-hour/opening-hour-detail.component';
import { OpeningHour } from 'app/shared/model/opening-hour.model';

describe('Component Tests', () => {
    describe('OpeningHour Management Detail Component', () => {
        let comp: OpeningHourDetailComponent;
        let fixture: ComponentFixture<OpeningHourDetailComponent>;
        const route = ({ data: of({ openingHour: new OpeningHour(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [OpeningHourDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OpeningHourDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OpeningHourDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.openingHour).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
