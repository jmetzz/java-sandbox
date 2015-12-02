package com.github.jmetzz.concurrency.deployable.poolAcknoledge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Subscribers {

    private List<Subscriber> subscribers;

    public Subscribers(URL jsonFile) {
        final ObjectMapper mapper = new ObjectMapper();
        // Convert JSON string from file to Object
        try {
            this.subscribers = mapper.readValue(jsonFile, new TypeReference<List<Subscriber>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkArgument(subscribers != null && !subscribers.isEmpty(),
                "Subscribers not initialized, please check that subscribers are configured in you json file!");
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public int size() {
        return this.subscribers.size();
    }

    public Subscriber get(int idx) {
        return subscribers.get(idx);
    }


    @Override
    public String toString() {
        return subscribers.toString();
    }


    public static class Subscriber {
        private String name;
        private int numWorkers;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumWorkers() {
            return numWorkers;
        }

        public void setNumWorkers(int numWorkers) {
            this.numWorkers = numWorkers;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("Name", name)
                    .add("Number of workers", numWorkers).toString();
        }

        @Override
        public boolean equals(Object o) {
            return name.equals(((Subscriber) o).getName());
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

}