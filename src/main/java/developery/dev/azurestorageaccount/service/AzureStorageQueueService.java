package developery.dev.azurestorageaccount.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.QueueServiceClientBuilder;
import com.azure.storage.queue.models.PeekedMessageItem;
import com.azure.storage.queue.models.SendMessageResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AzureStorageQueueService {
	
	final String ACCOUNT_END_POINT = "https://developerysa.queue.core.windows.net";
	final String QUEUE_END_POINT = ACCOUNT_END_POINT + "/sa-queue";
	String SAS_TOKEN= "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-28T20:28:10Z&st=2021-07-28T12:28:10Z&spr=https&sig=eRg7MXN2plaHqnU%2FbYhOBnAgkaGp2xUReJt71%2FnHC%2Bk%3D";


	public String insertQueue(String msg) {
		QueueClient queueClient = new QueueClientBuilder()
				.endpoint(QUEUE_END_POINT)
				.sasToken(SAS_TOKEN)
				.buildClient();
		
		SendMessageResult result = queueClient.sendMessage(LocalDateTime.now() + "_" + msg);
		
		System.out.println("msgId: " + result.getMessageId());
		
		return result.getMessageId();
		
	}
	

	public String peekQueue() {
		
		QueueClient queueClient = new QueueClientBuilder()
				.endpoint(QUEUE_END_POINT)
				.sasToken(SAS_TOKEN)
				.buildClient();
		
		PeekedMessageItem item = queueClient.peekMessage();
		
		System.out.println("peek: " + item.getBody().toString() 
				+ " . count: " + item.getDequeueCount() 
				+ " id: " +  item.getMessageId()
				+ " time: " +  item.getInsertionTime());
		
		return item.getMessageId();		
	}
	
	public int getMessageQueue(int count) {
		QueueClient queueClient = new QueueClientBuilder()
				.endpoint(QUEUE_END_POINT)
				.sasToken(SAS_TOKEN)
				.buildClient();

		List<String> msgIdList = queueClient.receiveMessages(count).stream()
				.map( message -> {
			
					System.out.println("messageId: " + message.getMessageId() 
						+ ", msg: " + message.getBody().toString() 
						+ ", popReceipt: " + message.getPopReceipt());
					return message.getMessageId();
				})
				.collect(Collectors.toList());		
		
		return msgIdList.size();
	}
	
//	@Test
	public int getMessageAndDeleteQueue(int count) {

		QueueClient queueClient = new QueueClientBuilder()
				.endpoint(QUEUE_END_POINT)
				.sasToken(SAS_TOKEN)
				.buildClient();

		List<String> msgIdList = queueClient.receiveMessages(count).stream()
				.map( message -> {
			
					System.out.println("messageId: " + message.getMessageId() 
						+ ", msg: " + message.getBody().toString() 
						+ ", popReceipt: " + message.getPopReceipt());
					
					queueClient.deleteMessage(message.getMessageId(), message.getPopReceipt()); // 삭제
					
					return message.getMessageId();
				})
				.collect(Collectors.toList());		
		
		return msgIdList.size();
	
	}
	
	public String listQueue() {
		
		QueueServiceClient queueServiceClient = new QueueServiceClientBuilder()
				.endpoint(ACCOUNT_END_POINT)
		        .sasToken(SAS_TOKEN)
		        .buildClient();

		
		List<String> queueNameList = queueServiceClient.listQueues().stream()
			.map(queue-> {
				System.out.println("queue name: " + queue.getName());
				return queue.getName();
			})
			.collect(Collectors.toList());
		
		return queueNameList.toString();
	}

}
