/////////////////////////////////////////////////////////////////////////////////////////////////////////

module Logic (
    input [1:0] op,
    input [31:0] A, B,
    output [31:0] Y
    );
    
    wire [31:0] AND, OR, XOR, NOR;

    assign AND = A & B;
    assign OR = A | B;
    assign XOR = A ^ B;
    assign NOR = A ~| B;

    FourMux LOGIC (
        .s(op[1:0]),
        .d00(AND),
        .d01(OR),
        .d10(XOR),
        .d11(NOR),
        .Y(Y)
        );
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
