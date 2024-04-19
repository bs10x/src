module SLT (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] slt;

    assign slt = (A - B < 0) ? 32'b1 : 32'b0; 

    Mux #(.data(32)) sltz (
        .s(op),
        .A(slt),
        .B(32'b0),
        .Y(Y)
        );
endmodule
