package org.hashcode.practice2021.model;

import java.util.List;

import org.hashcode.OutputData;

public class PizzeriaOutput implements OutputData {

    private final List<Delivery> deliveries;

    public PizzeriaOutput(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public long getSolutionScore() {
        return deliveries.stream()
                .mapToLong(delivery -> (long) Math.pow(delivery.getDistinctIngredients(), 2))
                .sum();
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    @Override
    public String toString() {
        return PizzeriaOutput.class.getSimpleName() + "{" +
                "deliveries=" + deliveries +
                "}";
    }

}
