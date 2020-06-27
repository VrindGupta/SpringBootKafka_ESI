package org.apache.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.json.JsonCustomObject;

public class KafkaProducerStream {

	public static void send(JsonCustomObject obj,String topic) {
		Producer<String, JsonCustomObject> kafkaProducer = KafkaProducerConfig.topicProducerMap.get(topic);
		kafkaProducer.send(new ProducerRecord<String, JsonCustomObject>(topic, "0", obj));
	}

}
