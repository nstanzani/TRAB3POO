package br.usp.icmc.ssc0103;

public class Student extends User {
    Student(String name, int remainingQuota, long time, int code) {
        this.name = name;
        this.remainingQuota = remainingQuota;
        this.time = time;
        this.type = "Estudante";
        this.code = code;
    }
}
