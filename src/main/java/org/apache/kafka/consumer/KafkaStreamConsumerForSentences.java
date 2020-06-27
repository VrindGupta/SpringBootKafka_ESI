package org.apache.kafka.consumer;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.json.JsonCustomObject;
import org.apache.kafka.jsonserializer.KafkaJsonDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class KafkaStreamConsumerForSentences {
	
	static Properties props = new Properties();
	static Consumer<String, JsonCustomObject> consumer = null;
	static Cluster cluster = null;
	static Session session = null; 
	static Timer timer = new Timer();

	static {
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test-consumer-group-sentences");
		consumer = new KafkaConsumer<String, JsonCustomObject>(props,new StringDeserializer(),
        		new KafkaJsonDeserializer<JsonCustomObject>(JsonCustomObject.class));
		consumer.subscribe(Collections.singleton("sentences"));
		cluster = Cluster.builder()
				.withProtocolVersion(ProtocolVersion.V3)
                .addContactPoint("127.0.0.1").build();
		session = cluster.connect("kafka");
	}

	public static void read() {
		
		timer.schedule(new readFromTopic(), 60000);

	}

	static class readFromTopic extends TimerTask {
		public void run() {
			ConsumerRecords<String, JsonCustomObject> record = consumer.poll(60000);
			for (ConsumerRecord<String, JsonCustomObject> re : record) {
				if(re.value().getMessage().length()>0) {
					session.execute("INSERT INTO kafka (message) VALUES ("+re.value().getMessage()+")");
				}
			}
			timer.schedule(new readFromTopic(), 60000);
		}
	}


}
