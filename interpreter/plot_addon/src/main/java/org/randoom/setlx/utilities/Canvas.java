package org.randoom.setlx.utilities;


import org.randoom.setlx.types.Value;

public class Canvas extends Value {

    @Override
    public Value clone() {
        return new Canvas();
    }

    @Override
    public void appendString(State state, StringBuilder sb, int tabs) {

    }

    @Override
    public int compareTo(CodeFragment other) {
        return 0;
    }

    @Override
    public long compareToOrdering() {
        return 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equalTo(Object other) {
        return false;
    }
}