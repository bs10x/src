module ClockDivisor #(parameter ratio = 1) (
    input reset,
    input clk_in,
    output clk_out
    );
    
    reg [ratio-1:0] count;
    always @ (posedge clk_in) begin
        if (reset) count <= 0;
        else count <= count + 1;
    end
    assign clk_out = &count;
endmodule

module Dimmer (
    input clk_sys,
    input rst,
    input u,
    input d,
    output A, B, C);
    
    wire clk;
    ClockDivisor #(.ratio(25)) clk_div (
        .clk_in(clk_sys),
        .reset(rst),
        .clk_out(clk)
    );

endmodule