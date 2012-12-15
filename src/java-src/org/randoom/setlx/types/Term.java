package org.randoom.setlx.types;

import org.randoom.setlx.boolExpressions.*;
import org.randoom.setlx.exceptions.IncompatibleTypeException;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.expressions.*;
import org.randoom.setlx.utilities.MatchResult;
import org.randoom.setlx.utilities.State;
import org.randoom.setlx.utilities.TermConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
    This class implements terms in the form of

        f(e1, e2, ..., en)

*/

public class Term extends IndexedCollectionValue {
    // functional character of the term;    e.g. f
    private String      mFunctionalCharacter;
    // arguments in inner body of the term; e.g. e1, e2, ..., en
    private SetlList    mBody;

    public Term(final String functionalCharacter) {
        this(functionalCharacter, 4);
    }

    public Term(final String functionalCharacter, final int initialCapacity) {
        this(functionalCharacter, new SetlList(initialCapacity));
    }

    public Term(final String functionalCharacter, final SetlList body) {
        mFunctionalCharacter = functionalCharacter;
        mBody                = body;
    }

    public Term clone() {
        return new Term(mFunctionalCharacter, mBody.clone());
    }

    public Iterator<Value> iterator() {
        return mBody.iterator();
    }

    public Iterator<Value> descendingIterator() {
        return mBody.descendingIterator();
    }

    /* Boolean operations */

    // viral operation
    public Term conjunction(final State state, final Expr other) throws SetlException {
        return (    new Conjunction(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval(state))
                    )
               ).toTerm(state);
    }
    public Term conjunctionFlipped(final State state, final Value other) {
        return (    new Conjunction(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term disjunction(final State state, final Expr other) throws SetlException {
        return (    new Disjunction(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval(state))
                    )
               ).toTerm(state);
    }
    public Term disjunctionFlipped(final State state, final Value other) {
        return (    new Disjunction(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this))
               ).toTerm(state);
    }

    // viral operation
    public Term implication(final State state, final Expr other) throws SetlException {
        return (    new Implication(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval(state))
                    )
               ).toTerm(state);
    }
    public Term implicationFlipped(final State state, final Value other) {
        return (    new Implication(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term negation(final State state) {
        return (    new Negation(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    /* type checks (sort of Boolean operation) */

    public SetlBoolean isTerm() {
        return SetlBoolean.TRUE;
    }

    /* arithmetic operations */

    // viral operation
    public Term difference(final State state, final Value subtrahend) {
        return (    new Difference(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(subtrahend)
                    )
               ).toTerm(state);
    }
    public Term differenceFlipped(final State state, final Value subtrahend) {
        return (    new Difference(
                        TermConverter.valueToExpr(subtrahend),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Value factorial(final State state) {
        return (    new Factorial(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term integerDivision(final State state, final Value divisor) {
        return (    new IntegerDivision(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(divisor)
                    )
               ).toTerm(state);
    }
    public Term integerDivisionFlipped(final State state, final Value divisor) {
        return (    new IntegerDivision(
                        TermConverter.valueToExpr(divisor),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term minus(final State state) {
        return (    new Minus(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term modulo(final State state, final Value modulo) {
        return (    new Modulo(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(modulo)
                    )
               ).toTerm(state);
    }
    public Term moduloFlipped(final State state, final Value modulo) {
        return (    new Modulo(
                        TermConverter.valueToExpr(modulo),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term power(final State state, final Value exponent) {
        return (    new Power(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(exponent)
                    )
               ).toTerm(state);
    }
    public Term powerFlipped(final State state, final Value exponent) {
        return (    new Power(
                        TermConverter.valueToExpr(exponent),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term product(final State state, final Value multiplier) {
        return (    new Product(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(multiplier)
                    )
               ).toTerm(state);
    }
    public Term productFlipped(final State state, final Value multiplier) {
        return (    new Product(
                        TermConverter.valueToExpr(multiplier),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term quotient(final State state, final Value divisor) {
        return (    new Quotient(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(divisor)
                    )
               ).toTerm(state);
    }
    public Term quotientFlipped(final State state, final Value divisor) {
        return (    new Quotient(
                        TermConverter.valueToExpr(divisor),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term sum(final State state, final Value summand) {
        return (    new Sum(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(summand)
                    )
               ).toTerm(state);
    }
    public Term sumFlipped(final State state, final Value summand) {
        return (    new Sum(
                        TermConverter.valueToExpr(summand),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    /* operations on collection values (Lists, Sets [, Strings]) */

    public void addMember(final Value element) {
        mBody.addMember(element);
    }

    public SetlList arguments() {
        return mBody.clone();
    }

    // viral operation
    public Term cardinality(final State state) {
        return (    new Cardinality(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term cartesianProduct(final State state, final Value other) {
        return (    new CartesianProduct(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other)
                    )
               ).toTerm(state);
    }
    public Term cartesianProductFlipped(final State state, final Value other) {
        return (    new CartesianProduct(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    // viral operation
    public Term collectionAccess(final State state, final List<Value> args) {
        final List<Expr> argExprs = new ArrayList<Expr>(args.size());
        for (final Value v : args) {
            argExprs.add(TermConverter.valueToExpr(v));
        }
        return (    new CollectionAccess(
                        TermConverter.valueToExpr(this),
                        argExprs)
               ).toTerm(state);
    }

    public Value collectionAccessUnCloned(final State state, final List<Value> args) throws SetlException {
        return mBody.collectionAccessUnCloned(state, args);
    }

    // viral operation
    public Term collectMap(final State state, final Value arg) {
        return (    new CollectMap(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(arg))
               ).toTerm(state);
    }

    public SetlBoolean containsMember(final Value element) {
        // Terms are inherently recursive, so search recursively
        return containsMemberRecursive(element); // this is implemented in CollectionValue.java
    }

    public Value firstMember() {
        return mBody.firstMember();
    }

    public SetlString functionalCharacter() {
        if (mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER)) {
            return new SetlString(Variable.FUNCTIONAL_CHARACTER_EXTERNAL);
        } else {
            return new SetlString(mFunctionalCharacter);
        }
    }

    public Value getMember(final int index) throws SetlException {
        return mBody.getMember(index);
    }

    public Value getMember(final Value index) throws SetlException {
        return mBody.getMember(index);
    }

    public Value getMemberUnCloned(final int index) throws SetlException {
        return mBody.getMemberUnCloned(index);
    }

    public Value getMemberUnCloned(final Value index) throws SetlException {
        return mBody.getMemberUnCloned(index);
    }

    public Value getMembers(final Value low, final Value high) throws SetlException {
        return mBody.getMembers(low, high);
    }

    public Value lastMember() {
        return mBody.lastMember();
    }

    public Value maximumMember() throws SetlException {
        return mBody.maximumMember();
    }

    public Value minimumMember() throws SetlException {
        return mBody.minimumMember();
    }

    // viral operation
    public Term productOfMembers(final State state) throws SetlException {
        return (    new ProductOfMembers(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    public void removeMember(Value element) {
        mBody.removeMember(element);
    }

    public Value removeFirstMember() {
        return mBody.removeFirstMember();
    }

    public Value removeLastMember() {
        return mBody.removeLastMember();
    }

    public void setMember(final Value index, final Value v) throws SetlException {
        mBody.setMember(index, v);
    }

    public int size() {
        return mBody.size();
    }

    // viral operation
    public Term sumOfMembers(final State state) {
        return (    new SumOfMembers(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm(state);
    }

    /* function call */

    // viral operation
    public Term call(final State state, final List<Expr> exprs, final List<Value> args) throws IncompatibleTypeException {
        if (mFunctionalCharacter.equalsIgnoreCase(VariableIgnore.FUNCTIONAL_CHARACTER)) {
            final List<Expr> argExprs = new ArrayList<Expr>(args.size());
            for (final Value v : args) {
                argExprs.add(TermConverter.valueToExpr(v));
            }
            return (    new Call(
                            new Variable(
                                TermConverter.valueToExpr(this).toString()
                            ),
                            argExprs
                        )
                   ).toTerm(state);
        } else {
            throw new IncompatibleTypeException(
                "Viral term expansion is only supported when performing a call on a term representing a variable."
            );
        }
    }

    /* string and char operations */

    public void appendString(final StringBuilder sb, final int tabs) {
        TermConverter.valueToCodeFragment(this, false).appendString(sb, 0);
    }

    public void canonical(final StringBuilder sb) {
        if (mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER)) {
            sb.append(Variable.FUNCTIONAL_CHARACTER_EXTERNAL);
        } else {
            sb.append(mFunctionalCharacter);
        }

        sb.append("(");
        mBody.canonical(sb, /* brackets = */ false);
        sb.append(")");
    }

    /* term operations */

    public MatchResult matchesTerm(final State state, final Value other) throws IncompatibleTypeException {
        if ( mFunctionalCharacter.equals(VariableIgnore.FUNCTIONAL_CHARACTER) ||
                ( other instanceof Term &&
                  ((Term) other).mFunctionalCharacter.equals(VariableIgnore.FUNCTIONAL_CHARACTER)
                )
           ) {
            return new MatchResult(true); // one of the terms is `ignore'
        } else if (mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER) && mBody.size() == 1) {
            // 'this' is a variable, which match anything (except ignore of course)
            final MatchResult result  = new MatchResult(true);
            // get name of variable
            final Value       idStr   = mBody.firstMember();
            if ( ! (idStr instanceof SetlString)) { // this is a wrong ^variable term
                return new MatchResult(false);
            }
            final String      id      = idStr.getUnquotedString();

            // look up if this variable is currently defined
            final Value       thisVal = state.findValue(id);
            if (thisVal != Om.OM) {
                return thisVal.matchesTerm(state, other);
            } else {
                // this undefined variable will be set to the value of `other' upon complete match
                result.addBinding(id, other);
                return result;
            }
        } else if ( ! (other instanceof Term)) {
            return new MatchResult(false);
        }
        // 'other' is a term
        final Term otherTerm = (Term) other;

        if ( ! mFunctionalCharacter.equals(otherTerm.mFunctionalCharacter)) {
            // functional characters do not match
            if ( ! (mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER_EXTERNAL) &&
                    otherTerm.mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER)   )
               ) {
                // however this only unacceptable when ! (this == 'Variable AND other == 'variable)
                // e.g 'Variable must match 'variable
                return new MatchResult(false);
            }
        } else if (mBody.size() != otherTerm.mBody.size()) {
            return new MatchResult(false);
        }

        // same functional character & same number of arguments
        final MatchResult     result      = new MatchResult(true);
        final Iterator<Value> thisIter    = iterator();
        final Iterator<Value> otherIter   = otherTerm.iterator();
        while (thisIter.hasNext() && otherIter.hasNext() && result.isMatch()) {
            MatchResult subResult   = thisIter.next().matchesTerm(state, otherIter.next());
            if (subResult.isMatch()) {
                result.addBindings(subResult);
            } else {
                return new MatchResult(false);
            }
        }

        return result;
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
    public int compareTo(final Value v) {
        if (this == v) {
            return 0;
        } else if (v instanceof Term) {
            final Term other = (Term) v;
                  int  cmp   = mFunctionalCharacter.compareTo(other.mFunctionalCharacter);
            if (cmp != 0 && (
                    (
                        mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER_EXTERNAL) &&
                        other.mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER)
                    ) || (
                        mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER) &&
                        other.mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER_EXTERNAL)
                    )
                )
            ) {
                // these are regarded as one and the same
                cmp = 0;
            }
            if (cmp != 0) {
                return cmp;
            }
            return mBody.compareTo(other.mBody);
        } else if (v instanceof ProcedureDefinition || v == Infinity.POSITIVE) {
            // only ProcedureDefinition and +Infinity are bigger
            return -1;
        } else {
            // everything else is smaller
            return 1;
        }
    }

    public boolean equalTo(final Value v) {
        if (this == v) {
            return true;
        } else if (v instanceof Term) {
            final Term other = (Term) v;
            if (mFunctionalCharacter.equals(other.mFunctionalCharacter)
                  || (
                    mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER_EXTERNAL) &&
                    other.mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER)
                ) || (
                    mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER) &&
                    other.mFunctionalCharacter.equals(Variable.FUNCTIONAL_CHARACTER_EXTERNAL)
                )
            ) {
                return mBody.equalTo(other.mBody);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private final static int initHashCode = Term.class.hashCode();

    public int hashCode() {
        return initHashCode + mFunctionalCharacter.hashCode() * 31 + mBody.hashCode();
    }
}
