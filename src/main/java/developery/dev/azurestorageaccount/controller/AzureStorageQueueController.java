package developery.dev.azurestorageaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developery.dev.azurestorageaccount.service.AzureStorageQueueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/azureStorageQueue")
public class AzureStorageQueueController {

	@Autowired
	AzureStorageQueueService service;
	
	@GetMapping("insertQueue/{msg}")
	public String insertQueue(
			@PathVariable("msg") String msg
			) {
		log.info("msg: {}", msg);
		
		return service.insertQueue(msg);		
	}
	
	@GetMapping("/peekQueue")
	public String peekQueue() {
		log.info("peekQueue.");
		
		return service.peekQueue();		
	}
	
	@GetMapping("/getMessageQueue/{count}")
	public int getMessageQueue(
			@PathVariable("count") int count)  {
		log.info("getMessageQueue. count: {}", count);
		
		return service.getMessageQueue(count);		
	}
	
	@GetMapping("/getMessageAndDeleteQueue/{count}")
	public int getMessageAndDeleteQueue(
			@PathVariable("count") int count)  {
		log.info("getMessageAndDeleteQueue. count: {}", count);
		
		return service.getMessageAndDeleteQueue(count);		
	}
	
	
	@GetMapping("/listQueue")
	public String listQueue() {
		log.info("listQueue");
		
		return service.listQueue();		
	}
}
