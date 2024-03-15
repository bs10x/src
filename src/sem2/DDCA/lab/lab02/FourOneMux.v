module FourOneMux(
    input [3:0] a,
    input [1:0] s,
    output o
);

wire s0, s1;

TwoOneMux M0(
    .a(a[0]),
    .b(a[1]),
    .s(s[0]),
    .o(o0)
);
TwoOneMux M1(
    .a(a[2]),
    .b(a[3]),
    .s(s[0]),
    .o(o1)
);
TwoOneMux M2(
    .a(o0),
    .b(o1),
    .s(s[1]),
    .o(o)
);

endmodule