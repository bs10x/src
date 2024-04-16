module FSM (
    input clk,
    input reset,
    input enable,
    output L, M, R
    );

    // state holding registers
    reg [2:0] state_prev, state_next;
    parameter idle = 3'b000;
    parameter frst = 3'b001;
    parameter scnd = 3'b011;
    parameter thrd = 3'b111;
    
    // update state based on inputs 
    always @ (posedge state_prev) begin
        case (state_prev)
            idle: state_next <= (enable) ? frst : idle;
            frst: state_next <= scnd;
            scnd: state_next <= thrd;
            thrd: state_next <= idle;
        endcase
    end


    // next state_prev logic
    always @ (posedge clk, posedge reset) begin
        if (reset) state_prev <= idle;
        else state_prev <= state_next;
    end

    // output logic
    assign {L, M, R} = state_next;
endmodule


/*
idle
frst
scnd
thrd