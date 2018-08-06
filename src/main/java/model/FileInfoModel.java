package model;
import org.springframework.web.multipart.MultipartFile;

public class FileInfoModel extends ModelBase {

    private String folderName;

    private MultipartFile multipartFile;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    private String rootLocation;
    //name of file
    private String image;

    private String destinationLocation;


    private FileInfoModel(FileInfoBuilder fileInfoBuilder) {
        this.folderName = fileInfoBuilder.folderName;

        this.multipartFile = fileInfoBuilder.multipartFile;

        this.rootLocation = fileInfoBuilder.rootLocation;

        this.image = fileInfoBuilder.image;

        this.destinationLocation = fileInfoBuilder.destinationLocation;
    }

    public static class FileInfoBuilder {
        public String getFolderName() {
            return folderName;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }

        private String folderName;

        private MultipartFile multipartFile;

        private String rootLocation;

        private String image;

        private String destinationLocation;

        public FileInfoBuilder() {

        }

        public FileInfoBuilder folderName(String folderName) {
            this.folderName = folderName;
            return this;
        }

        public FileInfoBuilder multipartFile(MultipartFile multipartFile) {
            this.multipartFile = multipartFile;
            return this;
        }

        public FileInfoBuilder rootLocation(String rootLocation) {
            this.rootLocation = rootLocation;
            return this;
        }

        public FileInfoBuilder image(String image) {
            this.image = image;
            return this;
        }


        public FileInfoBuilder destinationLocation(String destinationLocation) {
            this.destinationLocation = destinationLocation;
            return this;
        }

        public FileInfoModel build() {
            return new FileInfoModel(this);
        }


    }
}
