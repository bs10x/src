module FSM (
    input clk,
    input reset,
    input enable,
    output L, M, R
    );

    // state holding registers
    reg [2:0] state_p, state_n;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;
    
    // update state based on inputs 
    always @ (posedge state_p) begin
        if (enable) state_n <= S1;
        case (state_p)
            S1: state_n <= S2;
            S2: state_n <= S3;
            S3: state_n <= S0;
        endcase
    end


    // next state_p logic
    always @ (posedge clk, posedge reset) begin
        if (reset) state_p <= S0;
        else state_p <= state_n;
    end

    // output logic
    assign {L, M, R} = state_n;
endmodule



/***************************************
////////////////////////////////////////
// PROBLEM

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
state diagram:
    0 -> 000   // 0
    1 -> 100   // 4
    2 -> 110   // 6
    3 -> 111   // 7
r
pattern = [(1 << state) - 1] reversed
    0000    *
    0001
    0010
    0011
    0100    *
    0101
    0110    *
    0111    *
    1000

////////////////////////////////////////
***************************************/



