package interpreter.functions;

import interpreter.exceptions.IncompatibleTypeException;
import interpreter.exceptions.SetlException;
import interpreter.expressions.Expr;
import interpreter.types.SetlError;
import interpreter.types.SetlString;
import interpreter.types.Value;
import interpreter.utilities.ParseSetlX;

import java.util.List;

// eval(expr)              : evaluate a String of a SetlX expression

public class PD_eval extends PreDefinedFunction {
    public final static PreDefinedFunction DEFINITION = new PD_eval();

    private PD_eval() {
        super("eval");
        addParameter("setlX_expr");
        doNotChangeScope();
    }

    public Value execute(List<Value> args, List<Value> writeBackVars) throws SetlException {
        Value   exprArg = args.get(0);
        if ( ! (exprArg instanceof SetlString)) {
            throw new IncompatibleTypeException("Expression-argument '" + exprArg + "' is not a string.");
        }
        // get expression string to be parsed
        String  exprStr = exprArg.getUnquotedString();

        ParseSetlX.resetErrorCount();
        Expr expr = ParseSetlX.parseStringToExpr(exprStr);

        // eval and return result
        return expr.eval();
    }
}

