module clk_div #(parameter ratio = 1) (
    input clk,
    input rst,
    output clk_en
    );
    
    reg [ratio-1:0] clk_count;
    always @ (posedge clk) begin
        if (rst) clk_count <= 0;
        else clk_count <= clk_count + 1;
    end
    assign clk_en = &clk_count;
endmodule

module FSM (
    input clk,
    input rst,
    input l,
    input r,
    output reg LA, LB, LC,
    output reg RA, RB, RC
    );

    wire clk_en;
    clk_div #(.ratio(32)) CLKDIV (
        .clk(clk),
        .rst(rst),
        .clk_en(clk_en)
        );


    integer sL, sR;
    reg [2:0] pL, pR;
    
    always @ (posedge clk_en) begin
        
        if (rst) begin
            sL <= 0;
            sR <= 0;
        end

        else begin
            if (l) sL <= (sL + 1) % 4;
            if (r) sR <= (sR + 1) % 4;
            
            pL <= (1 << sL) - 1;
            pR <= (1 << sR) - 1;

            LA <= pL[0];
            LB <= pL[1];
            LC <= pL[2];

            RA <= pR[2];
            RB <= pR[1];
            RC <= pR[0];
            // {LA, LB, LC} <= pL[2:0];
            // {RA, RB, RC} <= pR[0:2];
        end
    end
endmodule





/*

////////////////////////////////////////
// PROBLEM

L[2:0] can be in state 2 when R[0:2] is in state 3
-> decouple the two






state diagram:
    0 -> 000   // 0
    1 -> 001   // 1
    2 -> 011   // 3
    3 -> 111   // 7

pattern = (1 << state) - 1
    0000    *
    0001    *
    0010
    0011    *
    0100
    0101
    0110
    0111    *
    1000



always @ clk posedge
    if (rst) state <= 0;
    else
        if (l)
        if (r)
*/
