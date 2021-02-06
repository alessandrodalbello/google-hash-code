package org.hashcode.practice2021.solvers.buckets;

class BucketsCounter {
    private final int teams4;
    private final int teams3;
    private final int teams2;

    private int buckets3 = 0, buckets2 = 0, buckets1 = 0;
    private int completedBuckets4 = 0, completedBuckets3 = 0, completedBuckets2 = 0;

    public BucketsCounter(int teams4, int teams3, int teams2) {
        this.teams4 = teams4;
        this.teams3 = teams3;
        this.teams2 = teams2;
    }

    public void newBucket() {
        buckets1 += 1;
    }

    public void increaseBucket(int bucketSize) {
        if (bucketSize == 3) {
            buckets3 += 1;
            buckets2 -= 1;
        } else if (bucketSize == 2) {
            buckets2 += 1;
            buckets1 -= 1;
        }
    }

    public void completeBucket(int bucketSize) {
        if (bucketSize == 4) {
            completedBuckets4 += 1;
            buckets3 -= 1;
        } else if (bucketSize == 3) {
            completedBuckets3 += 1;
            buckets2 -= 1;
        } else if (bucketSize == 2) {
            completedBuckets2 += 1;
            buckets1 -= 1;
        }
    }

    public void completeFinishedBucket(int bucketSize) {
        if (bucketSize == 3) {
            completedBuckets3 += 1;
            buckets3 -= 1;
        } else if (bucketSize == 2) {
            completedBuckets2 += 1;
            buckets2 -= 1;
        }
    }

    public void completeAlreadyCompletedBucket(int bucketSize) {
        if (bucketSize == 3) {
            completedBuckets4 += 1;
            completedBuckets3 -= 1;
        } else if (bucketSize == 2) {
            completedBuckets3 += 1;
            completedBuckets2 -= 1;
        } else if (bucketSize == 1) {
            completedBuckets2 += 1;
            buckets1 -= 1;
        }
        buckets1 -= 1;
    }

    public boolean canCreateNewBucket(int remainingPizzas) {
        int teams2Extras = 0;
        if (teams2 < (completedBuckets2 + buckets2 + buckets1)) {
            teams2Extras = (completedBuckets2 + buckets2 + buckets1) - teams2;
        }
        int teams3Extras = 0;
        if (teams3 < (completedBuckets3 + buckets3 + teams2Extras)) {
            teams3Extras = (completedBuckets3 + buckets3 + teams2Extras) - teams3;
        }
        int teams4Extras = 0;
        if (teams4 < (completedBuckets4 + teams3Extras)) {
            teams4Extras = (completedBuckets4 + teams3Extras) - teams4;
        }
        return teams4Extras == 0 && remainingPizzas > (buckets1 + teams2Extras + teams3Extras);
    }

    public boolean areTeams4Finished() {
        return completedBuckets4 == teams4;
    }

    public boolean areTeams3Finished() {
        return (completedBuckets3 + buckets3) == teams3;
    }

    public boolean areTeams2Finished() {
        return (completedBuckets2 + buckets2) == teams2;
    }

    public boolean areTeamsFinished() {
        return areTeams4Finished() && areTeams3Finished() && areTeams2Finished();
    }

    public boolean areCompletedTeamsValid() {
        return completedBuckets4 <= teams4 && completedBuckets3 <= teams3 && completedBuckets2 <= teams2;
    }

    public boolean areBucketsValid() {
        return buckets3 >= 0 && buckets2 >= 0 && buckets1 >= 0;
    }

    public int pizzasInBuckets() {
        return 4 * completedBuckets4 + 3 * completedBuckets3 + 2 * completedBuckets2;
    }
}
