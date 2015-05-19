package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public abstract class User {
    String name;
    int remainingQuota;
    int time;
    int penalty = 0;

    public void setName(String name) {
        this.name = name;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
