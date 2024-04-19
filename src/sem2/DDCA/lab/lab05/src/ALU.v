module Arithmetic (
    input [1:0] op,
    input [31:0] A, B,
    output reg [31:0] Y
    );

    always @ (*) begin
        case (op)
            2'b00: Y <= A + B;
            2'b10: Y <= A - B;
        endcase
    end
endmodule

module ALU (
    input [3:0] op,
    input [31:0] A, B,
    output reg [31:0] Y,
    output reg Z
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