package io.github.jhipster.application.web.rest.util;

import io.github.jhipster.application.web.rest.errors.GQBException;
import model.FileInfoModel;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Created by Anil on  5/23/18.
 */
public class MultiPartFileUtils {

    public static String writeFile(List<FileInfoModel> fileInfoModels) {
        for (FileInfoModel fileInfoModel : fileInfoModels) {
            writeFile(fileInfoModel);
        }
        return null;
    }

    private static String bulkMoveFile(List<FileInfoModel> fileInfoModels) {
        for (FileInfoModel fileInfoModel : fileInfoModels) {
            moveFile(fileInfoModel);
        }
        return null;
    }


    public static String moveFile(FileInfoModel fileSource) {
        String sourceFileLocation = fileSource.getRootLocation() + File.separator + fileSource.getFolderName();
        String sourceFileLocationPath = getNewFileName(fileSource);
        Path movefrom = Paths.get(sourceFileLocation + File.separator + sourceFileLocationPath);

        String destinationLocation = fileSource.getDestinationLocation() + File.separator + fileSource.getFolderName();
        String destinationLocationPath = getNewFileName(fileSource);
        File destinationFile = new File(destinationLocation);
        if (!destinationFile.exists()) {
            destinationFile.mkdirs();
        }
        Path target = Paths.get(destinationLocation + File.separator + destinationLocationPath);
        try {
            Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
            return destinationLocation + File.separator + destinationLocationPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Cause: " + e.getCause() + ", Message: " + e.getMessage();
        }
        //return null;
    }

    public static String getExtensionOfFile(String name) {
        String fileExtension = "";

        // If fileName domainObject not contain "." or starts with "." then it is not a valid file
        if (name.contains(".") && name.lastIndexOf(".") != 0) {
            fileExtension = name.substring(name.lastIndexOf(".") + 1);
        }


        return fileExtension;
    }


    /**
     * renames a file
     *
     * @param fileInfoModel
     * @param newName
     * @return
     */
    public static Boolean rename(FileInfoModel fileInfoModel, String newName) {
        String sourceFileLocation = fileInfoModel.getRootLocation() + File.separator + fileInfoModel.getFolderName();
        String sourceFileLocationPath = getNewFileName(fileInfoModel);
        String extension = getExtensionOfFile(fileInfoModel.getImage());
        String newFileName = newName + "." + extension;
        Path source = Paths.get(sourceFileLocation + File.separator + sourceFileLocationPath);

        try {
            Files.move(source, source.resolveSibling(newFileName));
            return true;
        } catch (NoSuchFileException noSuchFileException) {
            noSuchFileException.printStackTrace();
            throw new GQBException(noSuchFileException.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new GQBException(e.getMessage());
        }

    }

    /**
     * deletes a file
     *
     * @param fileInfoModel
     * @return
     */
    public static String deleteFile(FileInfoModel fileInfoModel) {
        String sourceFileLocation = fileInfoModel.getRootLocation() + File.separator + fileInfoModel.getFolderName();
        String sourceFileLocationPath = getNewFileName(fileInfoModel);
        Path sourcePath = Paths.get(sourceFileLocation + File.separator + sourceFileLocationPath);
        try {
            Files.deleteIfExists(sourcePath);
            return "Deleted";
        } catch (IOException e) {
            e.printStackTrace();
            throw new GQBException(e.getMessage());
        }
    }


    public static String writeFile(FileInfoModel fileInfoModel) {
        String folderFileLocation = fileInfoModel.getRootLocation() + fileInfoModel.getFolderName();
        File file = new File(folderFileLocation);
        if (!file.exists()) {
            file.mkdirs();
        }
        String newFileName = getNewFileName(fileInfoModel);
        Path path = Paths.get(folderFileLocation + File.separator + newFileName);
        try {
            Files.write(path, fileInfoModel.getMultipartFile().getBytes());
            return fileInfoModel.getFolderName() + File.separator + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GQBException(e.getMessage());
        }
    }

    /**
     * writes file to server and updates its name according the form data
     *
     * @param fileInfoModel
     * @param newName
     * @return
     */
    public static String writeandRenameFile(FileInfoModel fileInfoModel, String newName) {
        String folderFileLocation = fileInfoModel.getRootLocation() + fileInfoModel.getFolderName();
        File file = new File(folderFileLocation);
        if (!file.exists()) {
            file.mkdirs();
        }
        Path path = Paths.get(folderFileLocation + File.separator + newName);
        try {
            Files.write(path, fileInfoModel.getMultipartFile().getBytes());
            return fileInfoModel.getFolderName() + File.separator + newName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GQBException(e.getMessage());
        }
    }

    private static String getNewFileName(FileInfoModel fileInfoModel) {
        return (GQBStringUtils.isNotNull(fileInfoModel.getImage()) ? fileInfoModel.getImage() : fileInfoModel.getMultipartFile().getOriginalFilename());
    }

    public static byte[] readFile(FileInfoModel fileInfoModel) {
        byte[] bytes = new byte[0];
        if (fileInfoModel.getImage() != null) {
            String file = fileInfoModel.getRootLocation() + fileInfoModel.getFolderName() + File.separator + fileInfoModel.getImage();
            InputStream inputStream = null;
            try {
                File checkFile = new File(file);
                if (checkFile.exists()) {
                    inputStream = new FileInputStream(file);
                    return IOUtils.toByteArray(inputStream);
                }
                file = fileInfoModel.getRootLocation() + File.separator + "default.jpg";
                return defaultFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                throw new GQBException(e.getMessage());
            }
        }
        return bytes;
    }

    public static byte[] defaultFile(String file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return IOUtils.toByteArray(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GQBException(e.getMessage());
        }
        return null;
    }
}
