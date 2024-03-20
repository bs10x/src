module FourOneMux(
    input [3:0] d,
    input [1:0] s,
    output o
);

    wire s0, s1;

    TwoOneMux M0(
        .d0(d[0]),
        .d1(d[1]),
        .s(s[0]),
        .o(s0)
    );
    TwoOneMux M1(
        .d0(d[2]),
        .d1(d[3]),
        .s(s[1]),
        .o(s1)
    );
    or OUT(s0, s1, o);

endmodule
