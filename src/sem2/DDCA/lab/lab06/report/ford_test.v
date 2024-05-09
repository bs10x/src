`timescale 1ns / 1ps

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
    
    FORD ford (
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