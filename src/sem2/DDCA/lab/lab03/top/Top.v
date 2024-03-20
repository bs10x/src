module TwoFourDec (
    input [1:0] s,
    output [3:0] o
);

    wire ns0, ns1;
    not NOTSEL0 (s[0], ns0);
    not NOTSEL1 (s[1], ns1);

    and OUT0 (ns0, ns1, o[0]);
    and OUT1 (s[0], ns1, o[1]);
    and OUT2 (ns0, s[1], o[2]);
    and OUT3 (s[0], s[1], o[3]);
    
endmodule

module Top(
    input [1:0] s,
    output [3:0] AN
);
    // 2:4 DEC, input s[1:0], output not AN[3:0]
    TwoFourDec SEL (.s(s), .o(AN));


endmodule
