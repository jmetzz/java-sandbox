package com.github.jmetzz.laboratory.json_processing.jackson;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.MoreObjects;

import java.io.IOException;

public class CustomDeserializationExample {

    //TODO
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Item.class, new ItemDeserializer());
        mapper.registerModule(module);

        String json = "{\"id\": 1, \"itemName\": \"theItem\", \"createdBy\": 2}";

        Item readValue = mapper.readValue(json, Item.class);

        System.out.println(readValue);

    }

    public static class ItemDeserializer extends StdDeserializer<Item> {

        public ItemDeserializer() {
            this(null);
        }

        public ItemDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Item deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            int id = (Integer) node.get("id").numberValue();
            String itemName = node.get("itemName").asText();
            int userId = (Integer) node.get("createdBy").numberValue();

            return new Item(id, itemName, new User(userId, null));
        }
    }

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("name", name)
                    .toString();
        }
    }

    // @JsonDeserialize(using = ItemDeserializer.class)
    public static class Item {
        public int id;
        public String itemName;
        public User owner;

        public Item(int id, String itemName, User owner) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("itemName", itemName)
                    .add("owner", owner)
                    .toString();
        }
    }
}
