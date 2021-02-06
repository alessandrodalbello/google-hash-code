package org.hashcode.qualification2017.model;

import java.util.Map;
import java.util.Objects;

public class Endpoint {

    private final int id;
    private final int dataCenterLatency;
    private final Map<Integer, Integer> cachesLatency;

    public Endpoint(int id, int dataCenterLatency, Map<Integer, Integer> cachesLatency) {
        this.id = id;
        this.dataCenterLatency = dataCenterLatency;
        this.cachesLatency = cachesLatency;
    }

    public int getId() {
        return id;
    }

    public int getDataCenterLatency() {
        return dataCenterLatency;
    }

    public int getConnectedCachesNumber() {
        return cachesLatency.size();
    }

    public int getCacheLatency(int cacheId) {
        return cachesLatency.get(cacheId);
    }

    public boolean isCacheConnected(int cacheId) {
        return cachesLatency.containsKey(cacheId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Endpoint other = (Endpoint) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Endpoint.class.getSimpleName() + "{" +
                "id=" + id +
                ", dataCenterLatency=" + dataCenterLatency +
                ", cachesLatency=" + cachesLatency +
                "}";
    }

}
