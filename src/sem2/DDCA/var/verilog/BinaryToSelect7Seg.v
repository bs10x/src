module TwoFourDec (
    input wire [1:0] s,
    output wire [3:0] o);
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
    output wire [3:0] AN);

    wire [3:0] INVSEL;
    
    // 2:4 DEC, input s[1:0], output NOT AN[3:0]
    TwoFourDec SEL (
        .s(s[1:0]),
        .o(INVSEL[3:0])
    );

    assign AN = ~INVSEL;
endmodule

module FullAdder (
    input a,
    input b,
    input ci,
    output s,
    output co);
    
    wire so0, co0, co1;
    xor s0 (so0, a, b);
    xor SUM (s, s0, ci);
    and c0 (co0, a, b);
    and c1 (co1, s0, ci);
    or CARRY (co, c0, c1);
endmodule

module FourBitAdder(
    input wire [3:0] a,
    input wire [3:0] b,
    output wire [4:0] s);

    wire c0, c1, c2;
    FullAdder FA0(.a(a[0]), .b(b[0]), .ci(), .s(s[0]), .co(c0));
    FullAdder FA1(.a(a[1]), .b(b[1]), .ci(c0), .s(s[1]), .co(c1));
    FullAdder FA2(.a(a[2]), .b(b[2]), .ci(c1), .s(s[2]), .co(c0));
    FullAdder FA3(.a(a[3]), .b(b[3]), .ci(c2), .s(s[3]), .co(s[4]));
endmodule

module Drive7Seg(
    input wire [3:0] S,
    output reg [6:0] D);

    always @(*) begin
        case (S)
            4'b0000: D = 7'b1000000;    // 0
            4'b0001: D = 7'b1111001;    // 1
            4'b0010: D = 7'b0100100;    // 2
            4'b0011: D = 7'b0110000;    // 3
            4'b0100: D = 7'b0011001;    // 4
            4'b0101: D = 7'b0010010;    // 5
            4'b0110: D = 7'b0000010;    // 6
            4'b0111: D = 7'b1111000;    // 7
            4'b1000: D = 7'b0000000;    // 8
            4'b1001: D = 7'b0010000;    // 9
            4'b1010: D = 7'b0001000;    // a
            4'b1011: D = 7'b0000011;    // b
            4'b1100: D = 7'b1000110;    // c
            4'b1101: D = 7'b0100001;    // d
            4'b1110: D = 7'b0000110;    // e
            4'b1111: D = 7'b0001110;    // f
            default: D = 7'b1111111;    // f+1
        endcase
    end
endmodule

module BinaryTo7Seg(
    input wire [3:0] a,
    input wire [3:0] b,
    output wire [6:0] DISPLAY,
    output wire OVERFLOW);

    wire [4:0] S;
    wire [6:0] D;

    FourBitAdder ADD (
        .a(a),
        .b(b),
        .s(S[4:0])
    );
    Drive7Seg DRIVER (
        .S(S[3:0]),
        .D(D[6:0])
    );
    
    assign OVERFLOW = S[4];
    assign DISPLAY = D[6:0];
endmodule

module BinaryToSelect7Seg (
    input [3:0] a,
    input [3:0] b,
    input [1:0] s,
    output [3:0] AN,
    output [6:0] DISPLAY);

    wire [4:0] S;
    wire OVERFLOW;

    Select7Seg SEL (
        .s(s),
        .AN(AN[3:0])
    );

    BinaryTo7Seg DISPL (
        .a(a),
        .b(b),
        .DISPLAY(DISPLAY[6:0]),
        .OVERFLOW(OVERFLOW)
    );
endmodule

