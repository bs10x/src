module FourBitAdder(
input [3:0] a,
input [3:0] b,
output [4:0] s
);
wire c0, c1, c2, c3;

FullAdder fa0(.a(a[0]), .b(b[0]), .c(0), .s(s[0]));


endmodule