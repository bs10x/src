module FullAdder (input a, input b, input ci, output s, output co);
include HalfAdder;
wire y, c, x;
HalfAdder ab(.a(a), .b(b), .s(y), .c(c));
HalfAdder yci(.a(y), .b(ci), .s(s), .c(x));
or (c, x, s);

endmodule