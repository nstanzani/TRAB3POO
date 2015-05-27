package br.usp.icmc.ssc0103;

public abstract class User {
    String name;
    int code;
    int remainingQuota;
    long time;
    int penalty = 0;
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

    public void setPenalty(int penalty) {
        this.penalty = penalty;
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

    public int getPenalty() {
        return penalty;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.code + "\nNome: " + this.name + "\nTipo: " + this.type + "\nCota Restante: " + this.remainingQuota +
                "\nTempo que pode ficar com o livro: " + this.time + " dias\nPenalidade: " + this.penalty + " dias\n";
    }

    public String toFile() {
        return this.name + "," + this.remainingQuota + "," + this.time + "," + this.penalty + "," + this.type + "," + this.code + "\n";
    }
}
