module OneTwoDec (
    input s,
    output [1:0] o
);
    
    wire ns;
    
    not NOTSEL(s, ns);

    and O0(ns, ns, o[0]);
    and O1(s, s, o[1]);

endmodule