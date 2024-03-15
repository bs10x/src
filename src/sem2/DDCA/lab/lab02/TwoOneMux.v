module TwoOneMux(
    input a,
    input b,
    input s,
    output o
);
    wire sA, sB, sN;
    not nS(s, nS);
    and A(a, s, sA);
    and B(b, sN, sB);
    or OUT(sA, sB, o);

endmodule