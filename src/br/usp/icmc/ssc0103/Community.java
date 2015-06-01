package br.usp.icmc.ssc0103;

public class Community extends User {
    Community(String name, int remainingQuota, long time, int code) {
        this.name = name;
        this.remainingQuota = remainingQuota;
        this.time = time;
        this.type = "Comunidade";
        this.code = code;
    }
}
