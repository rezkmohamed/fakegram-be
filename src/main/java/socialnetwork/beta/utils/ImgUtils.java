package socialnetwork.beta.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import socialnetwork.beta.dto.ProfileDTO;

@Component
public class ImgUtils {
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;
	
	public boolean uploadProfileImg(MultipartFile file, ProfileDTO profile) {
		String filename = file.getOriginalFilename();
		String extension  = filename.substring(filename.lastIndexOf(".") + 1);

		if(extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
			String newProfilePic = UUID.randomUUID().toString()+ "." + extension;
			try {
				file.transferTo(new File(basePathFileSystem + newProfilePic));
				profile.setProPic(newProfilePic);
				
				return true;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public String fileImgToBase64Encoding(String proPic) throws IOException {
		String extension = proPic.substring(proPic.lastIndexOf(".") + 1);
		
		Path path = Paths.get(basePathFileSystem + proPic);
		byte[] content = null;
	    content = Files.readAllBytes(path);

		String img = "data:image/" + extension + ";base64, " + Base64.getEncoder().encodeToString(content);
		
		return img;
	}
}
