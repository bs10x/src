module TwoFourDec (
    input wire [1:0] s,
    output wire [3:0] o
);
    wire [1:0] ns;
    
    not NOTSEL0 (ns[0], s[0]);
    not NOTSEL1 (ns[1], s[1]);
    and OUT0 (o[0], ns[0], ns1);
    and OUT1 (o[1], s[0], ns1);
    and OUT2 (o[2], ns0, s[1]);
    and OUT3 (o[3], s[0], s[1]);
endmodule
