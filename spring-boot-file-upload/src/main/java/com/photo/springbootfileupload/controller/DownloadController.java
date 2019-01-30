package com.photo.springbootfileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadController {
	
   private static String UPLOADED_FOLDER ="D://UPLOADED//";
   @RequestMapping(value="/", method = RequestMethod.GET)
   public String showLoginPage(ModelMap model){
   	return "upload";
   }
   @RequestMapping(value="/upload", method = RequestMethod.POST)
   public String singleFileUpload(ModelMap model, @RequestParam("file")MultipartFile file) throws IOException {
	   if (file.isEmpty()) {
           model.put("message", "Please select a file to upload");
           return "uploadStatus";
       }
	  byte[] bytes = file.getBytes();
       Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
       Files.write(path, bytes);
	   

           model.put("message",
        		   "You successfully uploaded '" + file.getOriginalFilename() + "'");

      
	   
	   return"uploadStatus";
	   
	   
   }
   
   
}


