package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.ParameterDef;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_insertTitel extends PreDefinedProcedure {

    private final static ParameterDef CANVAS = createParameter("canvas");
    private final static ParameterDef TITEL = createParameter("titel");

    public final static PreDefinedProcedure DEFINITION = new PD_insertTitel();

    private PD_insertTitel(){
        super();
        addParameter(CANVAS);
        addParameter(TITEL);
    }
    @Override
    protected Value execute(State state, HashMap<ParameterDef, Value> args) throws SetlException {
        return new SetlString(String.valueOf(args.entrySet()));
    }
}