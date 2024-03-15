module FullAdder (input a, input b, input ci, output s, output co);s
    
    wire abs, abc, abscic;
    HalfAdder HF0(.a(a), .b(b), .s(abs), .c(abc));
    HalfAdder HF1(.a(abs), .b(ci), .s(s), .c(abscic));
    or CARRY (abc, abscic, co);

endmodule