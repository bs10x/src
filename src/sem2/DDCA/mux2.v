module mux2 
    #(parameter width = 8)
    (input [width-1:0] d0, d1,
    input s,
    output [width-1:0] y);

    assign y = s ? d1 : d0;

endmodule