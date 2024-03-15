module FourBitAdder(
input [3:0] a,
input [3:0] b,
output [4:0] s
);

    wire c0, c1, c2;

    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(0), .s(s[0]), .co(c0));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c0), .s(s[1]), .co(c1));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c1), .s(s[2]), .co(c0));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c2), .s(s[3]), .co(s[4]));

endmodule