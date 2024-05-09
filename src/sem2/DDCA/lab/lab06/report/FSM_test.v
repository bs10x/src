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

// jannis version
module BlinkingLightsTest();
    reg reset;
    reg left;
    reg right;

    wire [2:0] lights [0:1];

    reg clk;

    reg [2:0] exp_lights;
    reg exp_side;

    reg [10:0] vec_cnt, err_cnt;

    reg [8:0] testvec [0:99];

    always begin
        clk = 1; #50;
        clk = 0; #50;
    end

    initial begin
        $readmemb("testvec.txt",testvec);
        err_cnt = 0;
        vec_cnt = 0;
    end

    always @ (posedge clk) begin
        #20;
        {reset,left,right,exp_lights,exp_side} = testvec[vec_cnt];
        #60;
        if (lights[exp_side]!=exp_lights) begin
            $display("Error at %5d ns: r = %b left = %b right = %b", $time, reset,left,right);
            $display("       %b (%b expected)",lights[exp_side],exp_lights);
            if (exp_side==0)
                $display("On the right side");
            else
                $display("On the left side");
            err_cnt = err_cnt + 1;
        end
        else if (lights[~exp_side]!=0) begin
            $display("Error at %5d ns: r = %b left = %b right = %b", $time, reset,left,right);
            $display("       %b (000 expected)",lights[~exp_side]);
            if (exp_side==1)
                $display("On the right side");
            else
                $display("On the left side");
            err_cnt = err_cnt + 1;
        end
        vec_cnt = vec_cnt + 1;
        if ((testvec[vec_cnt][8:0] === 9'bxxxxxxxxx))
        begin
            // End of test, no more entries...
            $display ("%d tests completed with %d errors", vec_cnt, err_cnt);

            // Wait so that we can see the last result
            #20;

            // Terminate simulation
            $finish;
        end
    end
    FSM uut(
        .reset(reset),
        .eLeft(left),
        .eRight(right),
        .clk(clk),
        .stateR(lights[0]),
        .stateL(lights[1])
    );
endmodule