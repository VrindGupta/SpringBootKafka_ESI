package org.apache.kafka.jsonserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class KafkaJsonDeserializer<T> implements Deserializer {

	private Logger logger = LogManager.getLogger(this.getClass());

	private Class<T> type;

	public KafkaJsonDeserializer(Class type) {
		this.type = type;
	}

	public Object deserialize(String s, byte[] bytes) {
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj = mapper.readValue(bytes, type);
		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		return obj;
	}

	public void close() {

	}

	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub

	}
}
