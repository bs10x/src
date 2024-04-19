module ALU (
    input [3:0] OP,
    input [31:0] A, B,
    output [31:0] Y,
    output Z
);
    parameter ADD = 4'b0000;
    parameter SUB = 4'b0010;
    parameter AND = 4'b0100;
    parameter OR  = 4'b0101;
    parameter XOR = 4'b0110;
    parameter NOR = 4'b0111;
    parameter SLT = 4'b1010;

    


    

endmodule