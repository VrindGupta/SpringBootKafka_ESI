package org.spring.boot.client.reader;

import org.apache.kafka.consumer.KafkaStreamConsumer;
import org.apache.kafka.consumer.KafkaStreamConsumerForSentences;
import org.apache.kafka.json.JsonCustomObject;
import org.apache.kafka.producer.KafkaProducerStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientWordConsumer {
	static boolean first = false;
	@RequestMapping("/postWord")
	public void getWord(@RequestParam("word")String word){
		
		try{
			if(!first) {
				first = true;
				KafkaStreamConsumer.read();
				KafkaStreamConsumerForSentences.read();
			}
			JsonCustomObject obj = new JsonCustomObject("random", word);
			KafkaProducerStream.send(obj,"word");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
