module HalfAdder(input a, input b, output c, output s);

    and CARRY(c, a, b);
    xor SUM(s, a, b);

endmodule
