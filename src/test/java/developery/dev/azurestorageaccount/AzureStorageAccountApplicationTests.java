package developery.dev.azurestorageaccount;

import org.junit.jupiter.api.Test;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.QueueServiceClientBuilder;
import com.azure.storage.queue.models.PeekedMessageItem;
import com.azure.storage.queue.models.SendMessageResult;


class AzureStorageAccountApplicationTests {
	
	String SAS_TOKEN= "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-27T22:16:20Z&st=2021-07-27T14:16:20Z&spr=https&sig=jd45I6t%2BplLfQq3QBIGWSy3y3qHDA6eeaRJWY%2FKjBKM%3D";

//	@Test
	void insertQueue() {
		String queueURL = String.format("https://%s.queue.core.windows.net/%s", "developerysa", "sa-queue");
//		String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-26T22:06:05Z&st=2021-07-26T14:06:05Z&spr=https&sig=tloOs1h6wtbAjBkGWHR33jwxw%2BOR%2B2WSHBmUCM7tbeo%3D";
		QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).sasToken(SAS_TOKEN).buildClient();
		
		SendMessageResult result = queueClient.sendMessage("sentBySpring");
		
		System.out.println("id: " + result.getMessageId());
		
	}
	
//	@Test
	void peekQueue() {
		String queueURL = String.format("https://%s.queue.core.windows.net/%s", "developerysa", "sa-queue");
//		String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-26T22:06:05Z&st=2021-07-26T14:06:05Z&spr=https&sig=tloOs1h6wtbAjBkGWHR33jwxw%2BOR%2B2WSHBmUCM7tbeo%3D";
		QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).sasToken(SAS_TOKEN).buildClient();
		
		PeekedMessageItem item = queueClient.peekMessage();
		System.out.println("peek: " + item.getBody().toString() + " . count: " + item.getDequeueCount() + " id: " +  item.getMessageId() + " time: " +  item.getInsertionTime());
		
		
		
	}
	
//	@Test
	void getMessageQueue() {
		String queueURL = String.format("https://%s.queue.core.windows.net/%s", "developerysa", "sa-queue");
//		String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-26T22:06:05Z&st=2021-07-26T14:06:05Z&spr=https&sig=tloOs1h6wtbAjBkGWHR33jwxw%2BOR%2B2WSHBmUCM7tbeo%3D";
		QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).sasToken(SAS_TOKEN).buildClient();
		
		queueClient.receiveMessages(1).forEach( message -> {
			
			System.out.println("message: " + message.getMessageId() + " . " + message.getBody().toString() + " pop: " + message.getPopReceipt());
		});
		
		
	}
	
//	@Test
	void getMessageAndDeleteQueue() {
		String queueURL = String.format("https://%s.queue.core.windows.net/%s", "developerysa", "sa-queue");
//		String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-26T22:06:05Z&st=2021-07-26T14:06:05Z&spr=https&sig=tloOs1h6wtbAjBkGWHR33jwxw%2BOR%2B2WSHBmUCM7tbeo%3D";
		QueueClient queueClient = new QueueClientBuilder().endpoint(queueURL).sasToken(SAS_TOKEN).buildClient();
		
		queueClient.receiveMessages(1).forEach( message -> {
			String id = message.getMessageId();
			String pop = message.getPopReceipt();
			System.out.println("message: " + id + " . " + message.getBody().toString() + " pop: " + pop);
			queueClient.deleteMessage(id, pop);
		});
		
		
	}
	
	
	//@Test
	void listQueue() {
		String queueURL = String.format("https://%s.queue.core.windows.net/%s", "developerysa", "sa-queue");

		QueueServiceClient queueServiceClient = new QueueServiceClientBuilder().endpoint(queueURL)
		        .sasToken(SAS_TOKEN).buildClient();
//		new QueueClientBuilder().endpoint(queueURL).

		// metadata is map of key-value pair
//		queueClient.createWithResponse(metadata, Duration.ofSeconds(30), Context.NONE);
//		SendMessageResult result = queueClient.sendMessage("sentBySpring");
//		
//		System.out.println("id: " + result.getMessageId());
		
		queueServiceClient.listQueues().stream()
			.forEach(queue-> {
				System.out.println("queue name: " + queue.getName());
			});
	}
	

}
