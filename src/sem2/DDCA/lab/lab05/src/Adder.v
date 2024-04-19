module FullAdder (input a, input b, input ci, output s, output co);
    
    wire so0, co0, co1;

    xor s0 (so0, a, b);
    xor SUM (s, s0, ci);
    
    and c0 (co0, a, b);
    and c1 (co1, s0, ci);
    or CARRY (co, c0, c1);

endmodule

module Adder (
    input [31:0] A, B,
    output [31:0] Y
    );

    assign Y = A + B;


endmodule