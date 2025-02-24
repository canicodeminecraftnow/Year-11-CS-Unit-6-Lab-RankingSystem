public class User {
    private static final int MIN_RANK = -8;
    private static final int MAX_RANK = 8;
    private int rank;
    private int progress;

    public User() {
        this.rank = MIN_RANK;
        this.progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    public void incProgress(int activityRank) {
        if (activityRank < MIN_RANK || activityRank > MAX_RANK || activityRank == 0) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        if (rank == MAX_RANK) {
            return; // No more progress can be made once rank 8 is achieved.
        }

        int rankDifference = calculateRankDifference(activityRank, rank);

        if (rankDifference <= -2) {

            return;
        } else if (rankDifference == -1) {

            progress += 1;
        } else if (rankDifference == 0) {

            progress += 3;
        } else {

            progress += 10 * rankDifference * rankDifference;
        }


        while (progress >= 100 && rank < MAX_RANK) {
            progress -= 100;
            rank = nextRank(rank);
        }

        if (rank == MAX_RANK) {
            progress = 0;
        }
    }

    private int calculateRankDifference(int activityRank, int userRank) {
        // Special handling for the gap between -1 and 1 (no rank 0 exists).
        if (activityRank > 0 && userRank < 0) {
            return activityRank - userRank - 1;
        } else if (activityRank < 0 && userRank > 0) {
            return activityRank - userRank + 1;
        }
        return activityRank - userRank;
    }

    private int nextRank(int currentRank) {
        if (currentRank == -1) {
            return 1; // Skip rank 0.
        }
        return currentRank + 1;
    }

    @Override
    public String toString() {
        return "User{" + "rank=" + rank + ", progress=" + progress + '}';
    }
}
