module compare (input [3:0]  a, input [3:0] b, output eq);

    /*
    wire [3:0] c;
    assign c = ~(a ^ b);
    assign eq = &c;     // shorter than the previous version
    */
    
    assign eq = (a == b) ? 1 : 0; // really short
endmodule