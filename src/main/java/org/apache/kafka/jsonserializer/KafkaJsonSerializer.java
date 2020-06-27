package org.apache.kafka.jsonserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class KafkaJsonSerializer implements Serializer {

	private Logger logger = LogManager.getLogger(this.getClass());

	public byte[] serialize(String s, Object o) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsBytes(o);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return retVal;
	}

	public void close() {

	}

	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub

	}
}