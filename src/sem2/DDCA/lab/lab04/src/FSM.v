module FSM (
    input clk,
    input reset,
    input enable,
    output L, M, R
    );

    // state holding registers
    reg [2:0] state_left, state_right, nextstate;
    reg [1:0] selection;
    
    // update states based on inputs 
    always @ (posedge enable) begin
        
    end

    reg [2:0] S0 = 3'b000;
    reg [2:0] S1 = 3'b001;
    reg [2:0] S2 = 3'b011;
    reg [2:0] S3 = 3'b111;

    // next state logic
    always @ (posedge clk, posedge reset) begin

        if (selection[1]) begin
            case (state_left)
                S0: nextstate <= S1;
                S1: nextstate <= S2;
                S2: nextstate <= S3;
                S3: nextstate <= S0;
            endcase
        end
        if (selection[0]) begin
            case (state_right)
                S0: nextstate <= S1;
                S1: nextstate <= S2;
                S2: nextstate <= S3;
                S3: nextstate <= S0;
            endcase
        end


        // selection[1] <= left;
        // selection[0] <= right;


        // if (reset) begin
        //     state_left <= 0;
        //     state_right <= 0;
        //     selection  <= 0;
        // end
        // else begin
        //     if (selection[1]) begin
        //         if (~state_left) selection[1] <= 0;
        //         state_left <= state_left + 1;
        //     end
        //     if (selection[0]) begin
        //         if (~state_right) selection[0] <= 0;
        //         state_right <= state_right + 1;
        //     end
        // end
    end

    // output logic
    assign {L, M, R} = nextstate;
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



