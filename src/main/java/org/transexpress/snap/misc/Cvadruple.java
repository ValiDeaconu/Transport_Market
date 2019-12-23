package org.transexpress.snap.misc;

public class Cvadruple<F, S, T, FT> {
    private F first;
    private S second;
    private T third;
    private FT fourth;

    public Cvadruple(F first, S second, T third, FT fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public T getThird() {
        return third;
    }

    public void setThird(T third) {
        this.third = third;
    }

    public FT getFourth() {
        return fourth;
    }

    public void setFourth(FT fourth) {
        this.fourth = fourth;
    }
}
