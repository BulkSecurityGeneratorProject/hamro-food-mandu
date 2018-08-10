import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFood } from 'app/shared/model/food.model';
import { ImageService } from 'app/core/file/image.service';

@Component({
    selector: 'jhi-food-detail',
    templateUrl: './food-detail.component.html'
})
export class FoodDetailComponent implements OnInit {
    food: IFood;
    id: any;

    constructor(private activatedRoute: ActivatedRoute, private _imageService: ImageService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ food }) => {
            this.food = food;
        });
    }

    previousState() {
        window.history.back();
    }

    getImage(imgName) {
        if (imgName) {
            return this._imageService.getImage(imgName);
        }
    }
}
