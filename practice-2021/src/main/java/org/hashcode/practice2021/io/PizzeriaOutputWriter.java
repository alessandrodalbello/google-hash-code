package org.hashcode.practice2021.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.practice2021.model.Delivery;
import org.hashcode.practice2021.model.PizzeriaOutput;

public class PizzeriaOutputWriter extends OutputWriter<PizzeriaOutput> {

    private static PizzeriaOutputWriter instance = null;

    private PizzeriaOutputWriter() {
        // not visible
    }

    public static PizzeriaOutputWriter getInstance() {
        if (instance == null) {
            instance = new PizzeriaOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(PizzeriaOutput outputData, File outputFile) throws IOException {
        List<Delivery> deliveries = outputData.getDeliveries();
        int numberOfDeliveries = deliveries.size();
        List<String> deliveriesOutput = new ArrayList<>(numberOfDeliveries + 1);
        deliveriesOutput.add(String.valueOf(numberOfDeliveries));

        for (Delivery delivery : deliveries) {
            String deliveredPizzaIds = delivery.getPizzaIds().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            String deliveryDescription = delivery.getTeamSize() + " " + deliveredPizzaIds;

            deliveriesOutput.add(deliveryDescription);
        }

        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(deliveriesOutput);
    }

}
