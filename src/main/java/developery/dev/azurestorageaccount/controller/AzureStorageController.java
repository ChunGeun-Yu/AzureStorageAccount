package developery.dev.azurestorageaccount.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developery.dev.azurestorageaccount.service.AzureStorageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/azureStorage")
public class AzureStorageController {

	@Autowired
	AzureStorageService service;
	
	@GetMapping("/servicePrincipal/{containerName}/{blobName}")
	public String readBlobByServicePrincipalCredential(
			@PathVariable("containerName") String containerName
			, @PathVariable("blobName") String blobName) throws IOException {
		log.info("readBlobByServicePrincipalCredential. containerName: {}, blobName: {}", containerName, blobName);
		
		return service.readBlobByServicePrincipalCredential(containerName, blobName);		
	}
	
	@GetMapping("/managedId/{containerName}/{blobName}")
	public String readBlobBySystemManagedIdCredential(
			@PathVariable("containerName") String containerName
			, @PathVariable("blobName") String blobName) throws IOException {
		log.info("readBlobBySystemManagedIdCredential. containerName: {}, blobName: {}", containerName, blobName);
		
		return service.readBlobBySystemManagedIdCredential(containerName, blobName);		
	}
	
	@GetMapping("/accessKey/{containerName}/{blobName}")
	public String readBlobByAccessKeyCredential(
			@PathVariable("containerName") String containerName
			, @PathVariable("blobName") String blobName) throws IOException {
		log.info("readBlobByAccessKeyCredential. containerName: {}, blobName: {}", containerName, blobName);
		
		return service.readBlobByAccessKeyCredential(containerName, blobName);		
	}
	
	@GetMapping("/sas/{containerName}/{blobName}")
	public String readBlobBySasCredential(
			@PathVariable("containerName") String containerName
			, @PathVariable("blobName") String blobName) throws IOException {
		log.info("readBlobBySasCredential. containerName: {}, blobName: {}", containerName, blobName);
		
		return service.readBlobBySasCredential(containerName, blobName);		
	}
}
