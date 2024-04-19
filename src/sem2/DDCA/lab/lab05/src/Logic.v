module Mux #(parameter width = 8) (
    input s,
    input [width-1:0] A, B,
    output [width-1:0] Y);

    assign Y = s ? B : A;
endmodule

module Logic (
    input [1:0] op,
    input [31:0] A, B,
    output reg [31:0] Y
    );
    
    wire [31:0] AND, OR, XOR, NOR, ANDOR, XORNOR;

    assign AND = A & B;
    assign OR = A | B;
    assign XOR = A ^ B;
    assign NOR = A ~| B;

    Mux #(.width(32)) andor (
        .s(op[1]),
        .A(AND),
        .B(OR),
        .Y(ANDOR)
    );

    Mux #(.width(32)) xornor (
        .s(op[1]),
        .A(XOR),
        .B(NOR),
        .Y(XORNOR)
    );

    Mux #(.width(32)) operation (
        .s(op[1]),
        .A(ANDOR),
        .B(XORNOR),
        .Y(Y)
    );



    always @ (*) begin
        case (op)
            2'b00: Y <= A & B;
            2'b01: Y <= A | B;
            2'b10: Y <= A ^ B;
            2'b11: Y <= A ~| B;
        endcase
    end
endmodule