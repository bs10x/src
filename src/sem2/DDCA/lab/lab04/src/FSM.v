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





l
state diagram:
    0 -> 000   // 0
    1 -> 001   // 1
    2 -> 011   // 3
    3 -> 111   // 7
l
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


r
pattern = (8 >>> state) ~& 8
    0000    *
    0001
    0010
    0011
    0100    *
    0101
    0110    *
    0111    *
    1000

============================    0
    0001 << 0001
    0010
  - 0001

    0001
    
    &

    1110
    ~
    0001
    0001 << 0000

============================    1
    0001 << 0010
    0100
  - 0001

    0011
    
    &

    1101
    ~
    0010
    0001 << 0001



111 -> 111  // 7 -> 7
110 -> 011  // 6 -> 3
101 -> 001  // 5 -> 1
100 -> 000  // 4 -> 0


0 // 000 ->
1 // 001 ->
2 // 010 ->
3 // 011 ->

7 // 111 ->
6 // 110 ->
5 // 101 ->
4 // 100 ->

0 // 000
4 // 100
6 // 110
7 // 111



r
state diagram:
    0 -> 000   // 0
    1 -> 100   // 4
    2 -> 110   // 6
    3 -> 111   // 7
*/
