package com.photo.springbootfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFileUploadApplication {
	//private int maxUploadSizeInMb = 10*1024*1024;

	public static void main(String[] args){
		SpringApplication.run(SpringBootFileUploadApplication.class, args);
	}

}

//@Bean
/*public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
	
	
}*/
