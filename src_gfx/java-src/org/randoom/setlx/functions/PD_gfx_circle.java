package org.randoom.setlx.functions;

import org.randoom.setlx.utilities.StdDraw;

public class PD_gfx_circle extends GfxXYRFunction{
    public final static PreDefinedProcedure DEFINITION = new PD_gfx_circle();

    private PD_gfx_circle() {
        super();
    }

    @Override
    protected void executeStdDrawFunction(final Double x, final Double y, final Double r){
        StdDraw.circle( x, y, r );
    }

}
