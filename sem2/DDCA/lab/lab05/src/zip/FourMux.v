/////////////////////////////////////////////////////////////////////////////////////////////////////////

module FourMux #(parameter data = 32) (
    input [1:0] s,
    input [data-1:0] d00, d01, d10, d11,
    output [data-1:0] Y
    );

    wire [data-1:0] s0Z, s1Z;
    TwoMux #(.data(data)) mux0Z (
        .s(s[0]),
        .d0(d00),
        .d1(d01),
        .Y(s0Z)
        );

    TwoMux #(.data(data)) mux1Z (
        .s(s[0]),
        .d0(d10),
        .d1(d11),
        .Y(s1Z)
        );

    TwoMux #(.data(data)) muxZZ (
        .s(s[1]),
        .d0(s0Z),
        .d1(s1Z),
        .Y(Y)
        );
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
