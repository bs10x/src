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

module FSM (
    input clk,
    input reset,
    input enable,
    output A, B, C
    );

    reg [2:0] state_prev, state_next;

    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;

    // state holding register
    always @ (posedge clk, posedge reset) begin
        if (reset == 1) state_prev <= S0;
        else state_prev <= state_next;
    end

    // next state logic 
    always @ (posedge clk) begin
        case (state_prev)
            S0: state_next <= (enable == 1) ? S1 : S0;
            S1: state_next <= S2;
            S2: state_next <= S3;
            S3: state_next <= S0;
        endcase
    end

    // output logic
    assign {A, B, C} = state_prev;
endmodule


module Ford (
    input clk_sys,
    input left,
    input right,
    input reset,
    output LA, LB, LC,
    output RA, RB, RC
    );

    wire L, R;

    // clock divisor
    wire clk;
    ClockDivisor #(.ratio(25)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk)
        );

    // left blinker
    FSM LEFT (
        .clk(clk),
        .reset(reset),
        .enable(left & ~R),
        .C(LC),
        .B(LB),
        .A(L)
        );

    // right blinker
    FSM RIGHT (
        .clk(clk),
        .reset(reset),
        .enable(right & ~L),
        .C(RC),
        .B(RB),
        .A(R)
        );

        assign LA = L;
        assign RA = R;
endmodule

module FORD_test();
    
    // Inputs
    reg         rightI;
    reg         leftI;
    reg         reset;
    
    // Outputs
    wire [2:0]	rightO;
    wire [2:0]	leftO;
    
    // Test clock
    reg			clk ;
    
    // Expected outputs
    reg [2:0]	exp_rightO;
    reg [2:0]	exp_leftO;
    
    // Vector and Error counts
    reg [10:0]	vec_cnt, err_cnt;
    
    reg [8:0]  testvec [0:99]; // readmemh
    
    // The test clock generation
    always begin
        clk = 1; #50;		// clk is 1 for 50 ns
        clk = 0; #50;		// clk is 0 for 50 ns
    end					    // generate a 100 ns clock
    
    // Initialization
    initial
    begin
        $readmemh("testvectors_hex.txt", testvec);
        
        err_cnt = 0; // number of errors
        vec_cnt = 0; // number of vectors
    end
    
    // Tests
    always @ (posedge clk)		// trigger with the test clock
    begin
    
        #20;        
        {rightI, leftI, reset, exp_leftO, exp_rightO} = testvec[vec_cnt];
        
        
        #60;
        
        // Check if output is not what we expect to see
        if ((exp_rightO !== rightO) | (exp_leftO !== leftO))
        begin
            // Display message
            $display("Error at %5d ns: clk %d right = %b left = %b reset = %b", $time, clk, rightI, leftI, reset);	// %h displays hex
            $display("Right : %b (%b expected)", rightO, exp_rightO);
            $display("Left  : %b (%b expected)", leftO, exp_leftO);							// %b displays binary
            err_cnt = err_cnt + 1;																// increment error count
        end 
          
        vec_cnt = vec_cnt + 1;																	// next vector
        
        // We use === so that we can also test for X
        if (testvec[vec_cnt][8:0] === 9'bxxxxxxxxx)
        begin
            // End of test, no more entries...
            $display ("%d tests completed with %d errors", vec_cnt, err_cnt);
            
            // Wait so that we can see the last result
            #20;
            
            // Terminate simulation
            $finish;
        end
    end
    
    Ford ford (
        .clk_sys(clk),
        .left(leftI), 
        .right(rightI),
        .reset(reset),
        .LA(leftO[0]),
        .LB(leftO[1]),
        .LC(leftO[2]),
        .RA(rightO[2]),
        .RB(rightO[1]),
        .RC(rightO[0])
        );
    
endmodule