module FullAdder (input a, input b, input ci, output s, output co);s
wire abs, abc, abscic;
HalfAdder ab(.a(a), .b(b), .s(abs), .c(abc));
HalfAdder absci(.a(abs), .b(ci), .s(s), .c(abscic));
or carry (abc, abscic, co);
endmodule