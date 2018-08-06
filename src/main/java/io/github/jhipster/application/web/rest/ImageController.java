package io.github.jhipster.application.web.rest;


import io.github.jhipster.application.web.rest.util.GQBStringUtils;
import io.github.jhipster.application.web.rest.util.MultiPartFileUtils;
import model.FileInfoModel;
import model.GQBResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * Created by admin on 5/23/2018.
 */

/**
 * Handles request for upload and display image
 */
@RestController
@RequestMapping("/api")
public class ImageController {
    static String IMAGE_UPLOAD_LOCATION = "upload-images";
    static String ROOT = "src/main/webapp/content/";


    @Autowired
    private HttpServletRequest httpServletRequest;

    public ImageController(HttpServletRequest httpServletRequest) {
    }

    /**
     * Handles request to upload image
     *
     * @param uploadFile - {@link MultipartFile} file to be uploaded
     * @return - name of filename on successful upload
     */
    @PostMapping("/upload")
    public ResponseEntity<GQBResponseObj> singleFileUpload(@RequestParam("uploadFile") MultipartFile uploadFile) {

        FileInfoModel fileInfoModel = new FileInfoModel.FileInfoBuilder()
            .folderName(IMAGE_UPLOAD_LOCATION)
            .rootLocation(ROOT)
            .multipartFile(uploadFile)
            .build();
        String fileNameArr[] = uploadFile.getOriginalFilename().split("\\.");
        String newFilename = GQBStringUtils.getRandomName() + "." + fileNameArr[fileNameArr.length - 1];
        MultiPartFileUtils.writeandRenameFile(fileInfoModel, newFilename);
        GQBResponseObj fortunaResponseObj = new GQBResponseObj.GQBResponseObjBuilder().result(newFilename).message("success").build();
        return new ResponseEntity<>(fortunaResponseObj, HttpStatus.OK);
    }

    /**
     * Handles request to fetch image file
     *
     * @param fileName - name of file to be fetched
     * @return - file as stream of bytes
     * @throws FileNotFoundException - and exception is thrown if file is not found
     */
    @GetMapping("/display/{fileName}")
    public ResponseEntity<byte[]> displayFile(@PathVariable String fileName) throws FileNotFoundException {
        HttpHeaders httpHeaders = new HttpHeaders();
        FileInfoModel fileInfoModel = new FileInfoModel.
            FileInfoBuilder().image(fileName)
            .folderName(IMAGE_UPLOAD_LOCATION)
            .rootLocation(ROOT)
            .build();
        return new ResponseEntity<>(MultiPartFileUtils.readFile(fileInfoModel), httpHeaders, HttpStatus.OK);

    }


}
