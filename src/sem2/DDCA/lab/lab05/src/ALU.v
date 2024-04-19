module Mux #(parameter data = 8) (
    input s,
    input [data-1:0] A, B,
    output [data-1:0] Y);

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

    Mux #(.data(32)) operation (
        .s(op),
        .A(add),
        .B(sub),
        .Y(Y)
    );
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
    
    Mux #(.data(32)) operation (
        .s(op[1]),
        .A(ANDOR),
        .B(XORNOR),
        .Y(Y)
    );
endmodule

module ALU (
    input [3:0] op,
    input [31:0] A, B,
    output [31:0] Y,
    output Z
    );

    wire [31:0] out_AL;
    Arithmetic AL (
        .op(op[1:0]),
        .A(A),
        .B(B),
        .Y(out_AL)
        );

    Logic LL (
        .op(op[1:0]),
        .A(A),
        .B(B),
        .Y(Y)
        );

    always @ (*) begin
        case (op[3:2])
            2'b00: begin
                
            end
            2'b01: Y <= A - B;
            2'b10: Y <= A & B;
            default: Y <= 32'b0;
        endcase
        Z <= |Y;
    end









    // parameter ADD = 4'b0000;
    // parameter SUB = 4'b0010;
    // parameter AND = 4'b0100;
    // parameter OR  = 4'b0101;
    // parameter XOR = 4'b0110;
    // parameter NOR = 4'b0111;
    // parameter SLT = 4'b1010;
    // always @ (*) begin
    //     case (op)
    //         ADD: Y <= A  + B;
    //         SUB: Y <= A  - B;
    //         AND: Y <= A  & B;
    //         OR:  Y <= A  | B;
    //         XOR: Y <= A  ^ B;
    //         NOR: Y <= A ~| B;
    //         SLT: Y <= (A - B < 0) ? 32'b1 : 32'b0;
    //         default: Y <= 32'b0;
    //     endcase
    //     Z <= |Y;
    // end
endmodule