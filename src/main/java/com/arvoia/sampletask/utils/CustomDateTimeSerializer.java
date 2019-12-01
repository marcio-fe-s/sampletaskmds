package com.arvoia.sampletask.utils;

import java.io.IOException;
import java.sql.Timestamp;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateTimeSerializer extends StdSerializer<Timestamp> {

	private static final long serialVersionUID = 1L;

	public CustomDateTimeSerializer() {
		this(null);
	}

	public CustomDateTimeSerializer(Class<Timestamp> t) {
		super(t);
	}

	@Override
	public void serialize(Timestamp timestamp, JsonGenerator gen, SerializerProvider arg2)
			throws IOException {
		DateTime dateTime = new DateTime(timestamp.getTime());
		gen.writeString(dateTime.toString());
	}

}
