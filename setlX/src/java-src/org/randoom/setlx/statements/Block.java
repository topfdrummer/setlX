package org.randoom.setlx.statements;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.exceptions.TermConversionException;
import org.randoom.setlx.types.SetlList;
import org.randoom.setlx.types.Term;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.Environment;
import org.randoom.setlx.utilities.TermConverter;

import java.util.LinkedList;
import java.util.List;

/*
grammar rules:
initBlock
    : statement+
    ;

block
    : statement*
    ;

implemented here as:
      =========
     mStatements
*/

public class Block extends Statement {
    // functional character used in terms (MUST be class name starting with lower case letter!)
    private final static String FUNCTIONAL_CHARACTER = "^block";

    private List<Statement> mStatements;

    public Block() {
        this(new LinkedList<Statement>());
    }

    public Block(List<Statement> statements) {
        mStatements = statements;
    }

    public void add(Statement stmnt) {
        mStatements.add(stmnt);
    }

    public void execute() throws SetlException {
        for (Statement stmnt : mStatements) {
            stmnt.execute();
        }
    }

    public void exec() throws SetlException {
        execute();
    }

    /* string operations */

    public String toString(int tabs) {
        return toString(tabs, false);
    }

    public String toString(int tabs, boolean brackets) {
        String endl      = Environment.getEndl();
        int    stmntTabs = tabs;
        if (brackets) {
            stmntTabs += 1;
        }
        String result = "";
        if (brackets) {
            result += "{" + endl;
        }
        int count = 1;
        for (Statement stmnt: mStatements) {
            result += stmnt.toString(stmntTabs);
            if (count < mStatements.size()) {
                result += endl;
            }
            count++;
        }
        if (brackets) {
            result += endl + Environment.getLineStart(tabs) + "}";
        }
        return result;
    }

    /* term operations */

    public Term toTerm() {
        Term result = new Term(FUNCTIONAL_CHARACTER);

        SetlList stmntList = new SetlList();
        for (Statement s: mStatements) {
            stmntList.addMember(s.toTerm());
        }
        result.addMember(stmntList);

        return result;
    }

    public static Block termToStatement(Term term) throws TermConversionException {
        if (term.size() != 1 || ! (term.firstMember() instanceof SetlList)) {
            throw new TermConversionException("malformed " + FUNCTIONAL_CHARACTER);
        } else {
            SetlList    stmnts  = (SetlList) term.lastMember();
            Block       block   = new Block();
            for (Value v : stmnts) {
                block.add(TermConverter.valueToStatement(v));
            }
            return block;
        }
    }
}
