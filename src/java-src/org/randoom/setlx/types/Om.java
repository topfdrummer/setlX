package org.randoom.setlx.types;

import org.randoom.setlx.utilities.State;

public class Om extends Value {

    public final static Om OM = new Om();

    private Om() {  }

    @Override
    public Om clone() {
        // this value is atomic and can not be changed
        return this;
    }

    /* string and char operations */

    @Override
    public void appendString(final State state, final StringBuilder sb, final int tabs) {
        sb.append("om");
    }

    /* comparisons */

    /* Compare two Values.  Return value is < 0 if this value is less than the
     * value given as argument, > 0 if its greater and == 0 if both values
     * contain the same elements.
     * Useful output is only possible if both values are of the same type.
     * "incomparable" values, e.g. of different types are ranked as follows:
     * SetlError < Om < -Infinity < SetlBoolean < Rational & Real < SetlString
     * < SetlSet < SetlList < Term < ProcedureDefinition < +Infinity
     * This ranking is necessary to allow sets and lists of different types.
     */
    @Override
    public int compareTo(final Value v){
        if (v == OM) {
            return 0;
        } else if (v instanceof SetlError) {
            // only SetlError is smaller as Om
            return 1;
        } else {
            // everything else is bigger than Om
            return -1;
        }
    }

    @Override
    public boolean equalTo(final Value v) {
        if (v == OM) {
            return true;
        } else {
            return false;
        }
    }

    private final static int initHashCode = Om.class.hashCode();

    @Override
    public int hashCode() {
        return initHashCode;
    }
}

