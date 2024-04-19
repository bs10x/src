module Mux #(parameter width = 8) (
    input s,
    input [width-1:0] A, B,
    output [width-1:0] Y);

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

    Mux #(.width(32)) operation (
        .s(op),
        .A(add),
        .B(sub),
        .Y(Y)
    );
endmodule