module FullAdder (input a, input b, input ci, output so, output co);
    
    wire so0, co0, co1;

    xor s0 (so0, a, b);
    xor SUM (so, s0, ci);
    
    and c0 (co0, a, b);
    and c1 (co1, s0, ci);
    or CARRY (co, c0, c1);

endmodule

// module FourBitAdder (
//     input [3:0] a,
//     input [3:0] b,
//     output wire [4:0] S);
    
//     wire [2:0] c;

//     FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .so(S[0]), .co(c[0]));
//     FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c[0]), .so(S[1]), .co(c[1]));
//     FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c[1]), .so(S[2]), .co(c[2]));
//     FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c[2]), .so(S[3]), .co(S[4]));
// endmodule


module Adder16 (
    input [31:0] a,
    input [31:0] b,
    output [31:0] S);
    
    wire [2:0] c;

    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .so(S[0]), .co(c[0]));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c[0]), .so(S[1]), .co(c[1]));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c[1]), .so(S[2]), .co(c[2]));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c[2]), .so(S[3]), .co(S[4]));
endmodule

module Adder (
    input [31:0] A, B,
    output [31:0] Y
    );

    assign Y = A + B;


endmodule