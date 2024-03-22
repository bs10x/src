module TwoOneMux(
    input [1:0] d,
    input s,
    output Y);

    wire [1:0] sd;
    not NOTSEL(ns, s);
    
    and sd0(sd[0], d[0], ns);
    and sd1(sd[1], d[1], s);
    or Y0(Y, sd[0], sd[1]);
endmodule

module FourOneMux(
    input [3:0] d,
    input [1:0] s,
    output Y);

    wire [1:0] sd;

    TwoOneMux M0a(
        .d(d[1:0]),
        .s(s[0]),
        .Y(sd[0])
    );
    TwoOneMux M0b(
        .d(d[3:2]),
        .s(s[0]),
        .Y(sd[1])
    );
    TwoOneMux M1(
        .d({sd[1], sd[0]}),
        .s(s[1]),
        .Y(Y)
    );
endmodule
