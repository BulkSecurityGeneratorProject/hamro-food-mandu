import { Injectable } from '@angular/core';
import { FileService } from './file.service';
import { IMAGE_DISPLAY, SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import { ResponseModel } from 'app/shared/model/response.model';

type EntityResponseType = HttpResponse<ResponseModel>;
@Injectable({ providedIn: 'root' })
export class ImageService extends FileService {
    constructor(private http: HttpClient) {
        super();
    }

    getImage(imgName) {
        return '../../content/upload-images/' + imgName;
    }

    uploadRequest(uploadFile: File, url): Observable<EntityResponseType> {
        const formData: FormData = new FormData();
        formData.append('uploadFile', uploadFile);
        return this.http.post<ResponseModel>(SERVER_API_URL + url, formData, { observe: 'response' });
    }
}
