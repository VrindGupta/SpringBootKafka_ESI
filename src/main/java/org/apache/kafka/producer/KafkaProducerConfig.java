package org.apache.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.json.JsonCustomObject;
import org.apache.kafka.jsonserializer.KafkaJsonSerializer;

import java.util.HashMap;
import java.util.Properties;

public class KafkaProducerConfig {

	public static HashMap<String,Producer> topicProducerMap = new HashMap<String,Producer>();
	static {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		Producer<String, JsonCustomObject>
				wordkafkaProducer = new KafkaProducer<String, JsonCustomObject>(props, new StringSerializer(),
				new KafkaJsonSerializer());
		Producer<String, JsonCustomObject> sentencekafkaProducer = new KafkaProducer<String, JsonCustomObject>(props, new StringSerializer(),
				new KafkaJsonSerializer());
		topicProducerMap.put("word", wordkafkaProducer);
		topicProducerMap.put("sentences", sentencekafkaProducer);
	}

}
