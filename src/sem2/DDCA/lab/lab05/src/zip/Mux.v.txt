module Mux #(parameter data = 8) (
    input s,
    input [data-1:0] A, B,
    output [data-1:0] Y);

    assign Y = s ? B : A;
endmodule
