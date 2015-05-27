package br.usp.icmc.ssc0103;

public class Student extends User {
    Student(String name, int remainingQuota, int time){
        this.name = name;
        this.remainingQuota = remainingQuota;
        this.time = time;
        this.type = "Estudante";
    }
}
