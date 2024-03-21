module B4X1 (
    input wire [3:0] a,
    output reg [6:0] D);

    always @(*) begin
        case (a)
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

module B16X4 (
    input wire [15:0] a,
    output wire [3:0] AN,
    output wire [27:0] D);

    B4X1 X0 (
        .a(a[3:0]),
        .D(D[6:0])
    );
    B4X1 X1 (
        .a(a[7:4]),
        .D(D[13:7])
    );
    B4X1 X2 (
        .a(a[11:8]),
        .D(D[20:14])
    );
    B4X1 X3 (
        .a(a[15:12]),
        .D(D[27:21])
    );
    assign AN[0] = |a[3:0];
    assign AN[1] = |a[7:4];
    assign AN[2] = |a[11:8];
    assign AN[3] = |a[15:12];
endmodule