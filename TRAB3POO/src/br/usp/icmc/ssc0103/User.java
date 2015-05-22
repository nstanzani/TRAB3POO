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

    public String getName() {
        return name;
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public int getTime() {
        return time;
    }

    public int getPenalty() {
        return penalty;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.remainingQuota + ", " + this.time + ", " + this.penalty;
    }
}
