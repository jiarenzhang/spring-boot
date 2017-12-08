package com.gfl.common.utils.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class StringNullAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.value("");
            return;
        }
        writer.value(String.valueOf(value));
    }

    @Override
    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }

}
