module FullAdder (input a, input b, input ci, output s, output co);

wire abc;
wire abs;
wire abcic;
HalfAdder ab(.a(a), .b(b), .s(abs) .c(abc))
HalfAdder ab(.a(abs), .b(ci), .s(s), .c(abs))

or (abc, abcic, co)

endmodule