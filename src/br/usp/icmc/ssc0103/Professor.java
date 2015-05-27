package br.usp.icmc.ssc0103;

public class Professor extends User {
    Professor(String name, int remainingQuota, int time){
        this.name = name;
        this.remainingQuota = remainingQuota;
        this.time = time;
        this.type = "Professor";
    }
}

