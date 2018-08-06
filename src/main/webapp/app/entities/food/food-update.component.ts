import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFood } from 'app/shared/model/food.model';
import { FoodService } from './food.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';
import { IResturant } from 'app/shared/model/resturant.model';
import { ResturantService } from 'app/entities/resturant';
import { ImageService } from 'app/core/file/image.service';
import { ResponseModel } from 'app/shared/model/response.model';

@Component({
    selector: 'jhi-food-update',
    templateUrl: './food-update.component.html'
})
export class FoodUpdateComponent implements OnInit {
    private _food: IFood;
    isSaving: boolean;
    imageFile: File;
    fileName: string;
    categories: ICategory[];
    imgUploadUrl: string = 'api/upload';
    resturants: IResturant[];
    imgUrl: string;
    progress = { value: 0 };
    isVisible: boolean = false;
    defaultImgSrc = './assets/img/default-user.png';

    constructor(
        private jhiAlertService: JhiAlertService,
        private foodService: FoodService,
        private categoryService: CategoryService,
        private resturantService: ResturantService,
        private activatedRoute: ActivatedRoute,
        private http: HttpClient,
        private _imageService: ImageService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ food }) => {
            this.food = food;
        });
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resturantService.query().subscribe(
            (res: HttpResponse<IResturant[]>) => {
                this.resturants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.food.id !== undefined) {
            this.subscribeToSaveResponse(this.foodService.update(this.food));
        } else {
            this.subscribeToSaveResponse(this.foodService.create(this.food));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFood>>) {
        result.subscribe((res: HttpResponse<IFood>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }

    trackResturantById(index: number, item: IResturant) {
        return item.id;
    }

    get food() {
        return this._food;
    }

    set food(food: IFood) {
        this._food = food;
    }

    onSelectFile(event) {
        if (event.target.files && event.target.files[0]) {
            this.isVisible = true;
            var reader = new FileReader();

            reader.readAsDataURL(event.target.files[0]); // read file as data url
            this.imageFile = event.target.files[0];

            reader.onload = (event: FileReaderEvent) => {
                // called once readAsDataURL is completed
                this.imgUrl = event.target.result;
            };
            this.uploadImage();
        } else {
            this.resetFileUpload();
        }
    }

    resetFileUpload() {
        this.isVisible = false;
        this.fileName = '';
        this.imageFile = null;
        this.progress.value = 0;
        this.imgUrl = this.defaultImgSrc;
    }

    uploadImage() {
        // this._imageService.progress$.subscribe(
        //     (data: number) => {
        //         this.progress.value = data;
        //     });
        //
        this._imageService.uploadRequest(this.imageFile, this.imgUploadUrl).subscribe((res: HttpResponse<ResponseModel>) => {
            if (res.body.responseStatus) {
                this.fileName = res.body.result;
                this.food.image = this.fileName;
            }
        });
    }

    getImage(imgName) {
        return this._imageService.getImage(imgName);
    }
}
