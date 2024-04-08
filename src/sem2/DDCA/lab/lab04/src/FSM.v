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
    output LA, LB, LC,
    output RA, RB, RC
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
        end

        if (l) begin
            if (sL == 3) sL <= 0;
            else sL <= sL + 1;
        end

        if (r) begin
            if (sR == 3) sR <= 0;
            else sR <= sR + 1;
        end
        

        pL <= (1 << sL) - 1;

        // non blocking, so both can happen at the same time
        // need to be reg? idk..
        // {LA, LB, LC} <= (l) ? pattern[2:0] : 3'b000;
        // {RA, RB, RC} <= (r) ? pattern[0:2] : 3'b000;
    end
endmodule




// module thunderbird (
//     input clk,
//     input rst,
//     input left,
//     input right,
//     output LA, LB, LC, RA, RB, RC);
// 
// endmodule

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
