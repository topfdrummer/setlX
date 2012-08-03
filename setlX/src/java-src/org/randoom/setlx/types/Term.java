package org.randoom.setlx.types;

import org.randoom.setlx.boolExpressions.*;
import org.randoom.setlx.exceptions.IncompatibleTypeException;
import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.expressions.*;
import org.randoom.setlx.utilities.MatchResult;
import org.randoom.setlx.utilities.TermConverter;
import org.randoom.setlx.utilities.VariableScope;

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

    /* Boolean operations */

    // viral operation
    public Term conjunction(final Expr other) throws SetlException {
        return (    new Conjunction(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval())
                    )
               ).toTerm();
    }
    public Term conjunctionFlipped(final Value other) {
        return (    new Conjunction(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term disjunction(final Expr other) throws SetlException {
        return (    new Disjunction(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval())
                    )
               ).toTerm();
    }
    public Term disjunctionFlipped(final Value other) {
        return (    new Disjunction(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this))
               ).toTerm();
    }

    // viral operation
    public Term implication(final Expr other) throws SetlException {
        return (    new Implication(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(other.eval())
                    )
               ).toTerm();
    }
    public Term implicationFlipped(final Value other) {
        return (    new Implication(
                        TermConverter.valueToExpr(other),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term negation() {
        return (    new Negation(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    /* type checks (sort of Boolean operation) */

    public SetlBoolean isTerm() {
        return SetlBoolean.TRUE;
    }

    /* arithmetic operations */

    // viral operation
    public Term difference(final Value subtrahend) {
        return (    new Difference(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(subtrahend)
                    )
               ).toTerm();
    }
    public Term differenceFlipped(final Value subtrahend) {
        return (    new Difference(
                        TermConverter.valueToExpr(subtrahend),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Value factorial() {
        return (    new Factorial(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term integerDivision(final Value divisor) {
        return (    new IntegerDivision(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(divisor)
                    )
               ).toTerm();
    }
    public Term integerDivisionFlipped(final Value divisor) {
        return (    new IntegerDivision(
                        TermConverter.valueToExpr(divisor),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term minus() {
        return (    new Minus(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term modulo(final Value modulo) {
        return (    new Modulo(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(modulo)
                    )
               ).toTerm();
    }
    public Term moduloFlipped(final Value modulo) {
        return (    new Modulo(
                        TermConverter.valueToExpr(modulo),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term power(final Value exponent) {
        return (    new Power(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(exponent)
                    )
               ).toTerm();
    }
    public Term powerFlipped(final Value exponent) {
        return (    new Power(
                        TermConverter.valueToExpr(exponent),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term product(final Value multiplier) {
        return (    new Product(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(multiplier)
                    )
               ).toTerm();
    }
    public Term productFlipped(final Value multiplier) {
        return (    new Product(
                        TermConverter.valueToExpr(multiplier),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term quotient(final Value divisor) {
        return (    new Quotient(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(divisor)
                    )
               ).toTerm();
    }
    public Term quotientFlipped(final Value divisor) {
        return (    new Quotient(
                        TermConverter.valueToExpr(divisor),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term sum(final Value summand) {
        return (    new Sum(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(summand)
                    )
               ).toTerm();
    }
    public Term sumFlipped(final Value summand) {
        return (    new Sum(
                        TermConverter.valueToExpr(summand),
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    /* operations on collection values (Lists, Sets [, Strings]) */

    public void addMember(final Value element) {
        mBody.addMember(element);
    }

    public SetlList arguments() {
        return mBody.clone();
    }

    // viral operation
    public Term cardinality() {
        return (    new Cardinality(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    // viral operation
    public Term collectionAccess(final List<Value> args) {
        final List<Expr> argExprs = new ArrayList<Expr>(args.size());
        for (final Value v : args) {
            argExprs.add(TermConverter.valueToExpr(v));
        }
        return (    new CollectionAccess(
                        TermConverter.valueToExpr(this),
                        argExprs)
               ).toTerm();
    }

    public Value collectionAccessUnCloned(final List<Value> args) throws SetlException {
        return mBody.collectionAccessUnCloned(args);
    }

    // viral operation
    public Term collectMap(final Value arg) {
        return (    new CollectMap(
                        TermConverter.valueToExpr(this),
                        TermConverter.valueToExpr(arg))
               ).toTerm();
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

    // viral operation
    public Term multiplyMembers() throws SetlException {
        return (    new ProductOfMembers(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    public Value maximumMember() throws SetlException {
        return mBody.maximumMember();
    }

    public Value minimumMember() throws SetlException {
        return mBody.minimumMember();
    }

    public void removeMember(Value element) {
        mBody.removeMember(element);
    }

    public void removeFirstMember() {
        mBody.removeFirstMember();
    }

    public void removeLastMember() {
        mBody.removeLastMember();
    }

    public void setMember(final Value index, final Value v) throws SetlException {
        mBody.setMember(index, v);
    }

    public int size() {
        return mBody.size();
    }

    // viral operation
    public Term sumMembers() {
        return (    new SumOfMembers(
                        TermConverter.valueToExpr(this)
                    )
               ).toTerm();
    }

    /* function call */

    // viral operation
    public Term call(final List<Expr> exprs, final List<Value> args) throws IncompatibleTypeException {
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
                   ).toTerm();
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

    public MatchResult matchesTerm(final Value other) throws IncompatibleTypeException {
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
            final Value       thisVal = VariableScope.findValue(id);
            if (thisVal != Om.OM) {
                return thisVal.matchesTerm(other);
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
        while (thisIter.hasNext() && otherIter.hasNext()) {
            MatchResult subResult   = thisIter.next().matchesTerm(otherIter.next());
            if (subResult.isMatch()) {
                result.addBindings(subResult);
            } else {
                return new MatchResult(false);
            }
        }

        // all members match
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

