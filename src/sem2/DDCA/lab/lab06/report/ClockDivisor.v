
module ClockDivisor #(parameter ratio = 1) (
    input clk_in,
    input reset,
    output clk_out, dim_out
    );
    
    reg [ratio-1:0] clk_count, dim_sum;
    reg dim_carry;
    always @ (posedge clk_in) begin
        if (reset) begin 
            clk_count <= 0;
            dim_sum <= 0;
            dim_carry <=0;
        end else begin 
            clk_count <= clk_count + 1;
            {dim_carry, dim_sum} <= dim_sum + clk_count;
        end
    end
    assign clk_out = &clk_count;
    assign dim_out = dim_carry;
endmodule