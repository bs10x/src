module FullAdder (input a, input b, input ci, output so, output co);
    wire s;
    wire [1:0] c;

    xor s0 (s, a, b);
    xor SUM (so, s, ci);
    and c0 (c[0], a, b);
    and c1 (c[1], s, ci);
    or CARRY (co, c[0], c[1]);
endmodule

module FourBitAdder(
    input wire [3:0] a,
    input wire [3:0] b,
    output wire [4:0] s);
    
    wire [2:0] c;

    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .so(s[0]), .co(c[0]));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c[0]), .so(s[1]), .co(c[1]));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c[1]), .so(s[2]), .co(c[0]));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c[2]), .so(s[3]), .co(s[4]));
endmodule
