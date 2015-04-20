package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.types.Rational;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.ParameterDef;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_addGraph extends PreDefinedProcedure {

    private final static ParameterDef
            CANVAS = createParameter(" canvas ");

    private final static ParameterDef
            FUNCTIONDEFINITION = createParameter(" functiondefinition ");

    private final static ParameterDef
            GRAPHNAME = createOptionalParameter(" graphname ", Rational.ONE);

    private final static ParameterDef
            PLOTAREA = createOptionalParameter(" plotarea ", Rational.ONE);

    public final static PreDefinedProcedure
            DEFINITION = new PD_addGraph();

    private PD_addGraph() {
        super();
        addParameter(CANVAS);
        addParameter(FUNCTIONDEFINITION);
        addParameter(GRAPHNAME);
        addParameter(PLOTAREA);
    }

    @Override
    protected Value execute(State state, HashMap<ParameterDef, Value> args) throws SetlException {
        return null;
    }
}