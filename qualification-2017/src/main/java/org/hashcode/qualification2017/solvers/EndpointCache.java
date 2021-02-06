package org.hashcode.qualification2017.solvers;

import java.util.Objects;

class EndpointCache implements Comparable<EndpointCache> {
    private final int id;
    private final int latency;

    public EndpointCache(int id, int latency) {
        this.id = id;
        this.latency = latency;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EndpointCache other = (EndpointCache) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(EndpointCache other) {
        return Integer.compare(this.latency, other.latency);
    }
}
