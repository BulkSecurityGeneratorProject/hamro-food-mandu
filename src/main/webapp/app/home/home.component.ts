import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from 'app/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IFood } from 'app/shared/model/food.model';
import { FoodService } from 'app/entities/food';
import { ImageService } from 'app/core/file/image.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    foodList: IFood[];
    searchFoodList: IFood[];
    categoryList: ICategory[];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private foodService: FoodService,
        private _imageService: ImageService,
        private categoryService: CategoryService
    ) {}

    ngOnInit() {
        this.loadAllFood();
        this.loadAllCategory();
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
        this.loadAllFood();
    }

    private loadAllFood() {
        this.foodService
            .query({})
            .subscribe((res: HttpResponse<IFood[]>) => (this.foodList = res.body), (res: HttpErrorResponse) => console.log(res.error));
    }

    private loadAllCategory() {
        this.categoryService
            .query({})
            .subscribe(
                (res: HttpResponse<ICategory[]>) => (this.categoryList = res.body),
                (res: HttpErrorResponse) => console.log(res.error)
            );
    }

    getImage(imgName) {
        if (imgName) {
            return this._imageService.getImage(imgName);
        }
    }

    searchFoodlist(foodName) {
        this.searchFoodList = null;
        if (foodName) {
            this.loadAllFood();
            this.searchFoodList = this.foodList.filter(
                food => food.name.toUpperCase().includes(foodName) || food.categoryName.toUpperCase().includes(foodName)
            );
            if (this.searchFoodList.length == 0) this.searchFoodList = null;
        } else this.searchFoodList = null;
    }
}
