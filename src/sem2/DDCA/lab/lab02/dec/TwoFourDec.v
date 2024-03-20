module TwoFourDec (
    input [1:0] s,
    output [3:0] o
);

    wire ns0, ns1;
    not NOTSEL0(s[0], ns0);
    not NOTSEL1(s[1], ns1);

    and D0(ns0, ns1, o[0]);
    and D1(s[0], ns1, o[1]);
    and D2(ns0, s[1], o[2]);
    and D3(s[0], s[1], o[3]);
    
endmodule
