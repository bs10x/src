module HalfAdder(input a, input b, output c, output s);

    and CARRY(a, b, c);
    xor SUM(a, b, s);

endmodule