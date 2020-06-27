package org.apache.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.json.JsonCustomObject;
import org.apache.kafka.jsonserializer.KafkaJsonDeserializer;
import org.apache.kafka.producer.KafkaProducerStream;

import java.util.Collections;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class KafkaStreamConsumer {
	static Properties props = new Properties();
	static Consumer<String, JsonCustomObject> consumer = null;
	static Timer timer = new Timer();

	static {
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test-consumer-group");
		consumer = new KafkaConsumer<String, JsonCustomObject>(props,new StringDeserializer(),
        		new KafkaJsonDeserializer<JsonCustomObject>(JsonCustomObject.class));
		consumer.subscribe(Collections.singleton("word"));
	}

	public static void read() {
		
		timer.schedule(new readFromTopic(), 60000);

	}

	static class readFromTopic extends TimerTask {
		public void run() {
			ConsumerRecords<String, JsonCustomObject> record = consumer.poll(10000);
			StringBuilder sb = new StringBuilder();
			for (ConsumerRecord<String, JsonCustomObject> re : record) {
				sb.append(re.value().getMessage());
			}
			JsonCustomObject cust = new JsonCustomObject("waste", sb.toString());
			KafkaProducerStream.send(cust, "sentences");
			timer.schedule(new readFromTopic(), 60000);
		}
	}

}
