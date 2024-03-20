module TwoFourDec (
    input [1:0] s,
    output [3:0] d
);

    wire ns0, ns1;
    not NOTSEL0 (s[0], ns0);
    not NOTSEL1 (s[1], ns1);
    
    and D0 (ns0, ns1, d[0]);
    and D1 (s[0], ns1, d[1]);
    and D2 (ns0, s[1], d[2]);
    and D3 (s[0], s[1], d[3]);
    
endmodule
