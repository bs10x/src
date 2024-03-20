module FourOneMux(
    input [3:0] a,
    input [2:0] s,
    output o
);

    wire s0, s1;

    TwoOneMux M0(
        .d0(a[0]),
        .d1(a[1]),
        .s(s[0]),
        .o(s0)
    );
    TwoOneMux M1(
        .d0(s0),
        .d1(a[2]),
        .s(s[1]),
        .o(s1)
    );
    TwoOneMux M2(
        .d0(s1),
        .d1(a[3]),
        .s(s[2]),
        .o(o)
    );

endmodule
