/////////////////////////////////////////////////////////////////////////////////////////////////////////

module TwoMux #(parameter data = 32) (
    input s,
    input [data-1:0] d0, d1,
    output [data-1:0] Y
    );

    assign Y = s ? d1 : d0;
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module SLT (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] slt;

    assign slt = (A - B < 0) ? 32'b1 : 32'b0; 

    TwoMux SLTZ (
        .s(op),
        .d0(slt),
        .d1(32'b0),
        .Y(Y)
        );
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
