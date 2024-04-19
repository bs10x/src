module Mux #(parameter data = 8) (
    input s,
    input [data-1:0] A, B,
    output [data-1:0] Y
    );

    assign Y = s ? B : A;
endmodule

module Logic (
    input [1:0] op,
    input [31:0] A, B,
    output [31:0] Y
    );
    
    wire [31:0] AND, OR, XOR, NOR, ANDOR, XORNOR;

    assign AND = A & B;
    assign OR = A | B;
    assign XOR = A ^ B;
    assign NOR = A ~| B;

    Mux #(.data(32)) andor (
        .s(op[0]),
        .A(AND),
        .B(OR),
        .Y(ANDOR)
        );

    Mux #(.data(32)) xornor (
        .s(op[0]),
        .A(XOR),
        .B(NOR),
        .Y(XORNOR)
        );
    
    Mux #(.data(32)) andorxornor (
        .s(op[1]),
        .A(ANDOR),
        .B(XORNOR),
        .Y(Y)
        );
endmodule
