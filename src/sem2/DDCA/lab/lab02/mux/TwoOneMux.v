module TwoOneMux(
    input d0,
    input d1,
    input s,
    output o
);
    wire d0s, d1s, ns;
    not NOTSEL(s, ns);
    
    and d0(d0, ns, d0s);
    and d1(d1, s, d1s);
    or OUT(d0s, d1s, o);

endmodule
