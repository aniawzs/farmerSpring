package pl.annawyszomirskaszmyd.farmerspring.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FarmerAvatarService {
    final FarmerSession farmerSession;
    private static final String PATH_TO_AVATARS = "avatars";

    public enum UploadingAvatarResponse {
        INCORRECT_FORMAT, INCORRECT_SIZE, CORRECT_AVATAR, CREATE_FILE_EXCEPTION;
    }

    @Autowired
    public FarmerAvatarService(FarmerSession farmerSession) {
        this.farmerSession = farmerSession;
    }

    public UploadingAvatarResponse uploadAvatar(MultipartFile avatar, int id){
        id = farmerSession.getUserEntity().getId();

        if(!checkFileFormat(avatar)){
            return UploadingAvatarResponse.INCORRECT_FORMAT;
        }

        if(!checkFileSize(avatar)){
            return UploadingAvatarResponse.INCORRECT_SIZE;
        }

        try {
            copyAvatarToResourceFolder(avatar, id);
        } catch (IOException e) {
            return UploadingAvatarResponse.CREATE_FILE_EXCEPTION;
        }

        return UploadingAvatarResponse.CORRECT_AVATAR;
    }

    private void copyAvatarToResourceFolder(MultipartFile avatar, int id) throws IOException {
        Path pathToAvatar = Paths.get(PATH_TO_AVATARS + File.separator + id);

        createFileIfNotExists(pathToAvatar);

        Files.write(pathToAvatar, avatar.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void createFileIfNotExists(Path pathToAvatar) throws IOException {
        File file = pathToAvatar.toFile();

        if(!file.exists()){
            file.createNewFile();
        }
    }

    private boolean checkFileSize(MultipartFile avatar) {
        return avatar.getSize() <= 1024 * 1024 * 50;
    }

    private boolean checkFileFormat(MultipartFile avatar) {
        return avatar.getContentType() != null && (avatar.getContentType().contains("png") ||
                avatar.getContentType().contains("jpg") || avatar.getContentType().contains("jpeg"));
    }
}
