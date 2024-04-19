module Mux #(parameter data = 8) (
    input s,
    input [data-1:0] A, B,
    output [data-1:0] Y);

    assign Y = s ? B : A;
endmodule

module SLT (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] slt;

    assign slt = (A - B < 0) ? 32'b1 : 32'b0; 

    Mux #(.data(32)) operation (
        .s(op),
        .A(slt),
        .B(32'b0),
        .Y(Y)
    );
endmodule
