module TwoOneMux(
    input a,
    input b,
    input s,
    output o
);
    wire sA, sB, nS;
    not nS(s, nS);
    and A(a, s, sA);
    and B(b, nS, sB);
    or OUT(sA, sB, o);

endmodule