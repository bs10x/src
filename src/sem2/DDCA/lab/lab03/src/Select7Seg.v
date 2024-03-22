module TwoFourDec (
    input [1:0] s,
    output [3:0] Y);

    wire [1:0] ns;
    not ns0 (ns[0], s[0]);
    not ns1 (ns[1], s[1]);

    and Y0 (Y[0], ns[0], ns[1]);
    and Y1 (Y[1], s[0], ns[1]);
    and Y2 (Y[2], ns[0], s[1]);
    and Y3 (Y[3], s[0], s[1]);
endmodule

module Select7Seg(
    input wire [1:0] s,
    output wire [3:0] AN);

    wire [3:0] INVSEL;
    
    // 2:4 DEC, input s[1:0], output INV AN[3:0]
    TwoFourDec NOTAN (
        .s(s[1:0]),
        .Y(INVSEL[3:0])
    );

    assign AN = ~INVSEL;
endmodule
