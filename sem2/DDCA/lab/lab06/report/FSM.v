
module FSM (
    input clk,
    input reset,
    input eLeft,
    input eRight,
    output [2:0] stateL, stateR
    );

    // state holding registers
    reg [2:0] stateL_prev, stateL_next, stateR_prev, stateR_next;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;

    // update state based on inputs 
    always @ (*) begin
        case (stateL_prev)
            S0: stateL_next <= (eLeft && stateR_prev[0] == 0) ? S1 : S0;
            S1: stateL_next <= S2;
            S2: stateL_next <= S3;
            S3: stateL_next <= S0;
        endcase
        case (stateR_prev)
            S0: stateR_next <= (eRight && stateL_prev[0] == 0) ? S1 : S0;
            S1: stateR_next <= S2;
            S2: stateR_next <= S3;
            S3: stateR_next <= S0;
        endcase
    end

    // next state logic
    always @ (posedge clk, posedge reset) begin
        if (reset) begin
            stateL_prev <= S0;
            stateR_prev <= S0;
        end else begin 
            stateL_prev <= stateL_next;
            stateR_prev <= stateR_next;
        end
    end

    // output logic
    assign stateL = stateL_prev;
    assign stateR = stateR_prev;

endmodule