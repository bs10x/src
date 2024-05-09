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
        $readmemb("testvec.mem",testvec);
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
        end else if (lights[~exp_side]!=0) begin
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
        .left(left),
        .right(right),
        .clock(clk),
        .lights_right(lights[0]),
        .lights_left(lights[1])
    );
endmodule