package org.hashcode.practice2021.solvers.buckets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.practice2021.model.Delivery;
import org.hashcode.practice2021.model.Pizza;
import org.hashcode.practice2021.model.PizzeriaInput;
import org.hashcode.practice2021.model.PizzeriaOutput;

public class BucketsSolver implements Solver<PizzeriaInput, PizzeriaOutput> {

    @Override
    public PizzeriaOutput solve(PizzeriaInput inputData) {
        List<Pizza> pizzas = inputData.getPizzas();
        pizzas.sort(Comparator.comparing(Pizza::getNumberOfIngredients).reversed());
        assert pizzas.get(0).getNumberOfIngredients() > pizzas.get(pizzas.size() - 1).getNumberOfIngredients();

        List<Delivery> deliveries = chooseDeliveries(pizzas,
                inputData.get4PersonsTeams(), inputData.get3PersonsTeams(), inputData.get2PersonsTeams());
        return new PizzeriaOutput(deliveries);
    }

    private List<Delivery> chooseDeliveries(List<Pizza> pizzas, int teams4, int teams3, int teams2) {
        LinkedList<Bucket> inProgressBuckets = new LinkedList<>();
        List<Bucket> completedBuckets = new ArrayList<>();

        boolean areTeams4Completed = false, areTeams3Completed = false;
        BucketsCounter bucketsCounter = new BucketsCounter(teams4, teams3, teams2);
        boolean canCreateNewBucket = true;
        for (int i = 0, remainingPizzas = pizzas.size(); i < pizzas.size(); i++, remainingPizzas--) {
            Pizza pizza = pizzas.get(i);
            boolean isPizzaAssigned = false;

            int minimumWaste = -1;
            Bucket minimumWasteBucket = null;
            Iterator<Bucket> bucketsIterator = inProgressBuckets.iterator();
            while (!isPizzaAssigned && bucketsIterator.hasNext()) {
                Bucket bucket = bucketsIterator.next();
                int waste = bucket.calculateWaste(pizza);
                if (waste == 0) {
                    isPizzaAssigned = true;
                    if (addPizza(pizza, bucket, bucketsCounter, completedBuckets)) {
                        bucketsIterator.remove();
                    }
                }
                if (minimumWaste == -1 || waste < minimumWaste) {
                    minimumWasteBucket = bucket;
                    minimumWaste = waste;
                }
            }
            assert (minimumWasteBucket != null && minimumWaste >= 0) || inProgressBuckets.isEmpty();

            if (!isPizzaAssigned) {
                canCreateNewBucket &= bucketsCounter.canCreateNewBucket(remainingPizzas);
                if (canCreateNewBucket) {
                    Bucket newBucket = new Bucket(pizza);
                    inProgressBuckets.add(newBucket);
                    bucketsCounter.newBucket();
                } else {
                    assert minimumWasteBucket != null && minimumWaste >= 0;
                    if (addPizza(pizza, minimumWasteBucket, bucketsCounter, completedBuckets)) {
                        inProgressBuckets.remove(minimumWasteBucket);
                    }
                }
            }

            int bucketSize = -1;
            if (!areTeams4Completed && bucketsCounter.areTeams4Finished()) {
                areTeams4Completed = true;
                bucketSize = 3;
            } else if (areTeams4Completed && !areTeams3Completed && bucketsCounter.areTeams3Finished()) {
                areTeams3Completed = true;
                bucketSize = 2;
            } else if (areTeams4Completed && areTeams3Completed && bucketsCounter.areTeams2Finished()) {
                break;
            }
            if (bucketSize > 0) {
                bucketsIterator = inProgressBuckets.iterator();
                while (bucketsIterator.hasNext()) {
                    Bucket bucket = bucketsIterator.next();
                    if (bucket.size() == bucketSize) {
                        bucketsCounter.completeFinishedBucket(bucketSize);
                        completedBuckets.add(bucket);
                        bucketsIterator.remove();
                    }
                }
            }

            assert bucketsCounter.areCompletedTeamsValid();
        }

        Iterator<Bucket> bucketsIterator = inProgressBuckets.iterator();
        while (bucketsIterator.hasNext()) {
            Bucket bucket = bucketsIterator.next();
            int bucketSize = bucket.size();
            if (bucketSize != 1) {
                bucketsCounter.completeBucket(bucketSize);
                completedBuckets.add(bucket);
                bucketsIterator.remove();
            }
        }

        boolean areCompletedBucketsAvailable = true;
        bucketsIterator = inProgressBuckets.iterator();
        while (areCompletedBucketsAvailable && bucketsIterator.hasNext()) {
            Bucket uncompletedBucket = bucketsIterator.next();
            int bucketSize = uncompletedBucket.size();
            assert bucketSize == 1;

            List<Bucket> availableCompletedBuckets = completedBuckets.stream()
                    .filter(bucket -> (bucket.size() == 3 && !bucketsCounter.areTeams4Finished())
                            || bucket.size() == 2 && !bucketsCounter.areTeams3Finished())
                    .collect(Collectors.toList());
            if (!availableCompletedBuckets.isEmpty()) {
                int minimumWaste = -1;
                Bucket minimumWasteBucket = null;
                Pizza pizza = uncompletedBucket.getPizza(0);
                for (Bucket completedBucket : availableCompletedBuckets) {
                    int waste = completedBucket.calculateWaste(pizza);
                    if (minimumWaste == -1 || waste < minimumWaste) {
                        minimumWasteBucket = completedBucket;
                        minimumWaste = waste;
                    }
                }
                assert minimumWaste >= 0;

                bucketsCounter.completeAlreadyCompletedBucket(minimumWasteBucket.size());
                minimumWasteBucket.addPizza(pizza);
                bucketsIterator.remove();
            } else {
                areCompletedBucketsAvailable = false;
            }
        }

        while (inProgressBuckets.size() >= 2) {
            Bucket uncompletedBucket = inProgressBuckets.removeFirst();
            int bucketSize = uncompletedBucket.size();
            assert bucketSize == 1;

            int minimumWaste = -1;
            Bucket minimumWasteBucket = null;
            Pizza pizza = uncompletedBucket.getPizza(0);
            for (Bucket otherUncompletedBucket : inProgressBuckets) {
                int waste = otherUncompletedBucket.calculateWaste(pizza);
                if (minimumWaste == -1 || waste < minimumWaste) {
                    minimumWasteBucket = otherUncompletedBucket;
                    minimumWaste = waste;
                }
            }
            assert minimumWaste >= 0;
            assert minimumWasteBucket.size() == 1;

            bucketsCounter.completeAlreadyCompletedBucket(1);
            minimumWasteBucket.addPizza(pizza);
            inProgressBuckets.remove(minimumWasteBucket);
        }

        assert pizzas.size() == bucketsCounter.pizzasInBuckets() || bucketsCounter.areTeamsFinished();
        return completedBuckets.stream()
                .map(bucket -> new Delivery(bucket.size(), bucket.ingredients(), bucket.pizzaIds()))
                .collect(Collectors.toList());
    }

    private boolean addPizza(Pizza pizza, Bucket bucket, BucketsCounter bucketsCounter, List<Bucket> completedBuckets) {
        bucket.addPizza(pizza);
        int bucketSize = bucket.size();
        if (bucketSize == 4
                || (bucketSize == 3 && bucketsCounter.areTeams4Finished())
                || (bucketSize == 2 && bucketsCounter.areTeams3Finished())) {
            completedBuckets.add(bucket);
            bucketsCounter.completeBucket(bucketSize);
            return true;
        } else {
            bucketsCounter.increaseBucket(bucketSize);
            return false;
        }
    }
}
