/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HamrofoodmanduTestModule } from '../../../test.module';
import { ResturantDetailComponent } from 'app/entities/resturant/resturant-detail.component';
import { Resturant } from 'app/shared/model/resturant.model';

describe('Component Tests', () => {
    describe('Resturant Management Detail Component', () => {
        let comp: ResturantDetailComponent;
        let fixture: ComponentFixture<ResturantDetailComponent>;
        const route = ({ data: of({ resturant: new Resturant(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HamrofoodmanduTestModule],
                declarations: [ResturantDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ResturantDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResturantDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.resturant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
