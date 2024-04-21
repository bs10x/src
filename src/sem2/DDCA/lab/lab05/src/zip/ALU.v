/////////////////////////////////////////////////////////////////////////////////////////////////////////

module ALU (
    input [3:0] op,
    input [31:0] A, B,
    output [31:0] Y,
    output Z
    );

    wire [31:0] out_AU, out_LU, out_SLT;

    Arithmetic AU (
        .op(op[1]),
        .A(A),
        .B(B),
        .Y(out_AU)
        );
    Logic LU (
        .op(op[1:0]),
        .A(A),
        .B(B),
        .Y(out_LU)
        );
    SLT slt (
        .op(op[2]),
        .A(A),
        .B(B),
        .Y(out_SLT)
        );

    FourMux opearation (
        .s(op[3:2]),
        .d00(out_AU),
        .d01(out_LU),
        .d10(out_SLT),
        .d11(32'b0),
        .Y(Y)
        );

    assign Z = ~|Y;
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
