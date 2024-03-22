module TwoFourDec (
    input [1:0] s,
    output [3:0] Y);

    wire [1:0] ns;
    not NOTSEL0 (ns[0], s[0]);
    not NOTSEL1 (ns[1], s[1]);

    and Y0 (Y[0], ns[0], ns[1]);
    and Y1 (Y[1], s[0], ns[1]);
    and Y2 (Y[2], ns[0], s[1]);
    and Y3 (Y[3], s[0], s[1]);
endmodule
