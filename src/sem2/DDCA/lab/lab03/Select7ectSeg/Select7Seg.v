module TwoFourDec (
    input wire [1:0] s,
    output wire [3:0] o
);
    wire ns0, ns1;
    not NOTSEL0 (ns0, s[0]);
    not NOTSEL1 (ns1, s[1]);

    and OUT0 (o[0], ns0, ns1);
    and OUT1 (o[1], s[0], ns1);
    and OUT2 (o[2], ns0, s[1]);
    and OUT3 (o[3], s[0], s[1]);
endmodule

module Select7Seg(
    input wire [1:0] s,
    output wire [3:0] AN
);
    wire [1:0] sel;
    // wire [3:0] NOTAN;

    // 2:4 DEC, input s[1:0], output NOT AN[3:0]
    TwoFourDec SEL (
        .s(s[1:0]),
        .o(AN[3:0])     // .o(NOTAN)
    );

    assign AN = ~AN;    // ~NOTAN;
endmodule
