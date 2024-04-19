module ALU (
    input [3:0] op,
    input [31:0] A, B,
    output reg [31:0] Y,
    output reg Z
    );











    // parameter ADD = 4'b0000;
    // parameter SUB = 4'b0010;
    // parameter AND = 4'b0100;
    // parameter OR  = 4'b0101;
    // parameter XOR = 4'b0110;
    // parameter NOR = 4'b0111;
    // parameter SLT = 4'b1010;
    // always @ (*) begin
    //     case (op)
    //         ADD: Y <= A + B;
    //         SUB: Y <= A - B;
    //         AND: Y <= A & B;
    //         OR:  Y <= A | B;
    //         XOR: Y <= A ^ B;
    //         NOR: Y <= ~(A | B);
    //         SLT: Y <= (A - B < 0) ? 32'b1 : 32'b0;
    //         default: Y <= 32'b0;
    //     endcase
    //     Z <= |Y;
    // end
endmodule