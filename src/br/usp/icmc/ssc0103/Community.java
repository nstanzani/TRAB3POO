package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public class Community extends User {
    Community(String name, int remainingQuota, int time){
        this.name = name;
        this.remainingQuota = remainingQuota;
        this.time = time;
    }
}
