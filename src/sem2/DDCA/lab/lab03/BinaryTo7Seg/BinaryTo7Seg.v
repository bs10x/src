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

    wire c0, c1, c2;
    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .s(s[0]), .co(c0));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c0), .s(s[1]), .co(c1));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c1), .s(s[2]), .co(c0));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c2), .s(s[3]), .co(s[4]));
endmodule

module Drive7Seg(
    input wire [4:0] a,
    output wire [7:0] D
);

    wire [6:0] DISPL;
endmodule

module BinaryTo7Seg(
    input wire [3:0] a,
    input wire [3:0] b,
    output wire [6:0] DISPLAY,
    output wire OVERFLOW
);

    wire [4:0] S;
    wire [7:0] D;

    FourBitAdder ADD (
        .a(a),
        .b(b),
        .s(S)
    );

    Drive7Seg DRIVER (
        .a(S),
        .D(D)
    );
    assign OVERFLOW = D[7];
    assign DISPLAY = D[6:0];
endmodule


/*
    a
    -
 f | | b
 g  -
 e | | c
    -
    d  
*/


/*
s[4:0]
16 8 4 2 1

idea:
    instantiate FourBitAdder ADD
    build driver module that takes a 5 bit number(x[4:0])
    instantiate driver module, input is ADD.s, output is 

D[6:0]      {g, f, e, d, c, b, a}   {a, b, c, d, e, f, g}
0   -->     {1, 0, 0, 0, 0, 0, 0}   {0, 0, 0, 0, 0, 0, 1}
1   -->     {1, 1, 1, 1, 0, 0, 1}   {1, 0, 0, 1, 1, 1, 1}
2   -->     {0, 1, 0, 0, 1, 0, 0}   {0, 0, 1, 0, 0, 1, 0}
3   -->     {0, 1, 1, 0, 0, 0, 0}   {0, 0, 0, 0, 1, 1, 0}
4   -->     {0, 0, 1, 1, 0, 0, 1}   {1, 0, 0, 1, 1, 0, 0}
5   -->     {0, 0, 1, 0, 0, 1, 0}   {0, 1, 0, 0, 1, 0, 0}
6   -->     {0, 0, 0, 0, 0, 1, 0}   {0, 1, 0, 0, 0, 0, 0}
7   -->     {1, 1, 1, 1, 0, 0, 0}   {0, 0, 0, 1, 1, 1, 1}
8   -->     {0, 0, 0, 0, 0, 0, 0}   {0, 0, 0, 0, 0, 0, 0}
9   -->     {0, 0, 1, 0, 0, 0, 0}   {0, 0, 0, 0, 1, 0, 0}
a   -->     {0, 0, 0, 1, 0, 0, 0}   {0, 0, 0, 1, 0, 0, 0}
b   -->
c   -->
d   -->
e   -->
f   -->
f+1 -->
*/