/////////////////////////////////////////////////////////////////////////////////////////////////////////

module Arithmetic (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] add, sub;

    Adder adder (
        .A(A),
        .B(B),
        .S(add),
        .overflow()
        );

    // assign add = A + B;
    assign sub = A - B; 

    TwoMux addsub (
        .s(op),
        .d0(add),
        .d1(sub),
        .Y(Y)
        );
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
