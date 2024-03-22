module TwoFourDec (
    input wire [1:0] s,
    output wire [3:0] Q);

    wire [1:0] ns;
    not NOTSEL0 (ns[0], s[0]);
    not NOTSEL1 (ns[1], s[1]);

    and Q0 (Q[0], ns[0], ns[1]);
    and Q1 (Q[1], s[0], ns[1]);
    and Q2 (Q[2], ns[0], s[1]);
    and Q3 (Q[3], s[0], s[1]);
endmodule
