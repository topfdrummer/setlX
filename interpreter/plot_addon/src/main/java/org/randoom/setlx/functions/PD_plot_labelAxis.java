package org.randoom.setlx.functions;

import org.randoom.setlx.exceptions.SetlException;
import org.randoom.setlx.types.SetlString;
import org.randoom.setlx.types.Value;
import org.randoom.setlx.utilities.Canvas;
import org.randoom.setlx.utilities.ConnectJFreeChart;
import org.randoom.setlx.utilities.ParameterDef;
import org.randoom.setlx.utilities.State;

import java.util.HashMap;

public class PD_plot_labelAxis extends PreDefinedProcedure {

    private final static ParameterDef CANVAS = createParameter("canvas");
    private final static ParameterDef XLABEL = createParameter("xLabel");
    private final static ParameterDef YLABEL = createParameter("yLabel");
    public final static PreDefinedProcedure DEFINITION = new PD_plot_labelAxis();

    private PD_plot_labelAxis() {
        super();
        addParameter(CANVAS);
        addParameter(XLABEL);
        addParameter(YLABEL);
    }

    @Override
    protected Value execute(State state, HashMap<ParameterDef, Value> args) throws SetlException {
        Value canvas = args.get(CANVAS);
        Value xLabel = args.get(XLABEL);
        Value yLabel = args.get(YLABEL);
        ConnectJFreeChart.getInstance().labelAxis((Canvas) canvas, xLabel.toString().replace("\"", ""), yLabel.toString().replace("\"", ""));
        return new SetlString("Added xLabel \""+xLabel+"\" and yLabel \""+yLabel+"\" to Canvas");
    }
}