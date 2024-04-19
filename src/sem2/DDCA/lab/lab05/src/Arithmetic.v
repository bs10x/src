module Mux #(parameter data = 8) (
    input s,
    input [data-1:0] A, B,
    output [data-1:0] Y
    );

    assign Y = s ? B : A;
endmodule

module Arithmetic (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] add, sub;

    assign add = A + B; 
    assign sub = A - B; 

    Mux #(.data(32)) addsub (
        .s(op),
        .A(add),
        .B(sub),
        .Y(Y)
        );
endmodule
