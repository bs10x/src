module TwoOneMux(
    input d0,
    input d1,
    input s,
    output Y);

    wire [1:0] sd;
    not NOTSEL(ns, s);
    
    and sd0(sd[0], d0, ns);
    and sd1(sd[1], d1, s);
    or Y0(Y, sd[0], sd[1]);
endmodule

module FourOneMux(
    input [3:0] d,
    input [1:0] s,
    output Y);

    wire [1:0] sd;

    TwoOneMux M0(
        .d0(d[0]),
        .d1(d[1]),
        .s(s[0]),
        .Y(sd[0])
    );
    TwoOneMux M1(
        .d0(d[2]),
        .d1(d[3]),
        .s(s[1]),
        .Y(sd[1])
    );
    or Y0(sd[0], sd[1], Y);

endmodule
