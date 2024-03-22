module FullAdder (input a, input b, input ci, output s, output co);
    wire so0, co0, co1;

    xor s0 (so0, a, b);
    xor SUM (s, s0, ci);
    and c0 (co0, a, b);
    and c1 (co1, s0, ci);
    or CARRY (co, c0, c1);
endmodule


module FourBitAdder(
    input wire [3:0] a,
    input wire [3:0] b,
    output wire [4:0] s);
    
    wire [2:0] c;

    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .s(s[0]), .co(c[0]));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c[0]), .s(s[1]), .co(c[1]));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c[1]), .s(s[2]), .co(c[0]));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c[2]), .s(s[3]), .co(s[4]));
endmodule
