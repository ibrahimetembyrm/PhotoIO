package com.photo.springbootfileupload.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class UploadController {
	
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
    
         
	   return "uploadStatus";
	   
	   
   }
   @RequestMapping(value="/listFiles", method = RequestMethod.GET)
 
   public String ListingItems(ModelMap model){
	   try (Stream<Path> walk = Files.walk(Paths.get(UPLOADED_FOLDER))) {
     	  
  			List<String> result = walk.filter(Files::isRegularFile)
  					.map(x -> x.getFileName().toString())
  					.collect(Collectors.toList());
  			
  			model.put("files",result );

  		} 
         catch (IOException e) {
  			e.printStackTrace();
  		}
		

   	return "listFiles";
   }
   @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public String DeletingItems(@RequestParam String filename) {
	   Path path = Paths.get(UPLOADED_FOLDER + filename);
	   try {
		Files.delete(path);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return "redirect:/listFiles";
	   
   }
   @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<Resource> DownloadItems(@RequestParam String filename) {
	   Path path = Paths.get(UPLOADED_FOLDER + filename);
	   
	   
	   Resource file = null;
	try {
		file = new UrlResource(path.toUri());
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
               "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    

   }
   
}


