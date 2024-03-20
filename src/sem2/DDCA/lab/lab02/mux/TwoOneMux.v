module TwoOneMux(
    input d0,
    input d1,
    input s,
    output o
);
    wire d0s, d1s, ns;
    not NOTSEL(ns, s);

    and d0sel(d0s, d0, ns);
    and d1sel(d1s, d1, s);
    or OUT(o, d0s, d1s);

endmodule
