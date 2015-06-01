package br.usp.icmc.ssc0103;

import java.time.LocalDate;
import java.util.Optional;

public abstract class User {
    String name;
    int code;
    int remainingQuota;
    long time;
    Optional<LocalDate> penalty = Optional.empty();
    String type;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }

    public void setPenalty(Optional<LocalDate> penalty){
        this.penalty = penalty;
    }

    public void calculatePenalty(LocalDate base, long days) {
        if(penalty.isPresent()) {
            this.penalty = Optional.of(this.penalty.get().plusDays(days));
        }
        else {
            this.penalty = Optional.of(base.plusDays(days));
        }
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public long getTime() {
        return time;
    }

    public LocalDate getPenalty() {
        if(this.penalty.isPresent())
            return penalty.get();
        return LocalDate.MIN;
    }

    public String getType() {
        return type;
    }

    public void decRemainingQuota(){
        this.remainingQuota--;
        return;
    }

    public void incRemainingQuota(){
        this.remainingQuota++;
        return;
    }

    @Override
    public String toString() {
        if(this.penalty.isPresent()) {
            return "Codigo: " + this.code + "\nNome: " + this.name + "\nTipo: " + this.type + "\nCota Restante: " + this.remainingQuota +
                    "\nTempo que pode ficar com o livro: " + this.time + " dias\nPenalidade: " + this.penalty + " dias\n";
        }
        else{
            return "Codigo: " + this.code + "\nNome: " + this.name + "\nTipo: " + this.type + "\nCota Restante: " + this.remainingQuota +
                    "\nTempo que pode ficar com o livro: " + this.time + " dias\nSem penalidade alguma\n";
        }
    }

    public String toFile() {
        if(penalty.isPresent()) {
            return this.name + "," + this.remainingQuota + "," + this.time + "," + this.penalty.get().getYear() +
                    "," + this.penalty.get().getMonthValue() + "," + this.penalty.get().getDayOfMonth() + "," + this.type + "," + this.code;
        }
        else
            return this.name + "," + this.remainingQuota + "," + this.time + ",null,null,null," + this.type + "," + this.code;
    }
}
