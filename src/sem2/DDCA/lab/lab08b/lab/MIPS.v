`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: ETH Zurich
// Engineer: Frank K. Gurkaynak
// 
// Create Date:    18:20:55 03/21/2011 
// Design Name: 
// Module Name:    MIPS 
// Project Name:   Lab 8
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////

module MIPS(
   input CLK,                   // Clock signal
   input RESET,                 // Reset Active low will set back the Program counter
   output [31:0] IOWriteData,   // IO Data to be written to the interface
   output  [3:0] IOAddr,        // IO Address we use 4 bits, could also be more
   output        IOWriteEn,     // 1: There is a valid IO Write
   input  [31:0] IOReadData     // 32bit input from the I/O interface        
   );

   // The MIPS processor
	// (Mostly) based on the descriptions on the textbook 
	// Digital Design and Computer Architecture
	// Chapter 7, Section 7.3, pages 368-381

//////////////////////////////////////////////////////////////////////////////////
   // Signal Declarations
	// Refer to Figure 7.14 page 379 for names 

   // Instruction Decoding
	wire [31:0] Instr;     // The output of the Instruction memory
   wire [31:0] SignImm;   // 32-bit extended Immediate value
   wire  [4:0] WriteReg;  // Address of the register for write back

	// Address controls 
	reg  [31:0] PC;        // The Program counter (registered)
	wire [31:0] PCbar;     // Next state value of the Program counter, PC' in the diagram
   wire [31:0] PCCalc;    // Calculated value for the (next) PC
   wire [31:0] PCJump;    // Value for immediate jump
	wire [31:0] PCBranch;  // Value calculated for the branch instructions
	wire [31:0] PCPlus4;   // The current value of PC + 4, default next memory address

   // ALU related
   wire [31:0] SrcA;      // One input of the ALU
   wire [31:0] SrcB;      // Other input of the ALU
	wire [31:0] ALUResult; // The output of the ALU
	wire        Zero;      // The Zero flag, 1: if ALUResult == 0 
	
	// Data Memory
	wire [31:0] WriteData; // The output of Register File port 2,
	wire [31:0] ReadData;  // Output of the Data Memory
	wire [31:0] Result;    // End result that will be written back to register file
	wire        MemWrite;  // Write Enable for the Memory
	
	// Control Signals
	wire        Jump;      // A direct jump instruction has been issued
	wire        MemtoReg;  // 1: Copy data from Data Memory to Register File
	wire        Branch;    // 1: We have a branch instruction
	wire        PCSrc;     // We have a Branch AND ALUResult is zero, we will branch
	wire  [5:0] ALUControl;// Control signals for the ALU
	wire        ALUSrc;    // 0: Register file, 1: Immediate value
	wire        RegDst;    // Destination Register 1: Instr[15:11] 0: Instr[20:15]
	wire        RegWrite;  // 1: We will write back to the RegisterFile

   	// Memory Mapped I/O Signals
   	wire        IsIO;      // 1: if Address is in I/O range 0x00007ff0 to 0x0007fff
	wire        IsMemWrite;// 1: if MemWrite and not IsIO, we write to memory, not to the IO
	wire [31:0] ReadMemIO;  // Read from either Memory or I/O
	
//////////////////////////////////////////////////////////////////////////////////
// The Main Part of the MIPS processor 

   ////////////////////////////////////
	// The Program Counter
	always @ ( posedge CLK, posedge RESET ) 
	begin
		if    (RESET	  == 1'b1) PC <= 32'h00002FFC; // default program counter 
		else                    PC <= PCbar;        // Copy next value to present
	end
   
	// Calculation of the next PC value
	assign PCPlus4  = PC + 4;                             // By default the PC increments by 4
   	assign PCBranch = PCPlus4 + {SignImm[29:0],2'b00};    // The branch address see Page 373, Fig 7.10
	assign PCCalc = PCSrc ? PCBranch : PCPlus4;           // Multiplexer selects Branch or only +4
   	assign PCJump = {PCPlus4[31:28], Instr[25:0], 2'b00}; // The Jump value
	assign PCbar  = Jump  ? PCJump   : PCCalc;            // Multiplexer selects Jump or Normal

   /////////////////////////////////////
   // Instantiate the Instruction Memory
   InstructionMemory i_imem (
      // TODO PART 1 !!
      .A(PC[7:2]),   // Address of the Instruction, [5:0]
      .RD(Instr)   // Value at Address, [31:0]
   );
										
   // Sign extension, replicate the MSB of the Immediate value 
	assign SignImm = {{16{Instr[15]}},Instr[15:0]};

   // Determine the Write Back address for the Register File
	assign WriteReg = RegDst ? Instr[15:11] : Instr[20:16];
	
   ////////////////////////////////////
   // Instantiate the Register File
   RegisterFile i_regf (
      .A1(Instr[25:21]),   // Address for First Register
      .A2(Instr[20:16]),   // Address for Second Register
      .A3(WriteReg),       // Address for Write Back
      .RD1(SrcA),          // First output directly connected to ALU
      .RD2(WriteData),     // Second output
      .WD3(Result),        // Output of ALU or Data Memory
      .WE3(RegWrite),      // From the control unit
      .CLK(CLK)            // System Clock (10 MHz)
   );


   ////////////////////////////////////
   // ALU: first determine the inputs, and then instantiate the ALU
	assign SrcB = ALUSrc ? SignImm : WriteData ; // ALU input is either immediate or from register

   ALU i_alu (
      // TODO PART 1 !!
      .a(SrcA),
      .b(SrcB),
      .aluop(ALUControl[3:0]),
      .result(ALUResult),
      .zero(Zero)
   );
					 
   // Generate the PCSrc signal that tells to take the branch
	assign PCSrc = Branch & Zero;                // simple AND
   
   ////////////////////////////////////
	// Instantiate the Data Memory
   DataMemory i_dmem (
      // TODO PART 1 !!
      .CLK(CLK),   // Clock signal rising edge
      .A(ALUResult[7:2]),       // Address for 64 locations [5:0]
      .WE(MemWrite),      // Write Enable 1: Write 0: no write
      .WD(WriteData),      // 32-bit data in
      .RD(ReadData)       // 32-bit read data
   );

   // Memory Mapped I/O
   assign IsIO = (ALUResult[31:4] == 28'h00007ff) ? 1 : 0; // 1: when datamemory address falls into I/O  address range

   // TODO PART 1 !!
   assign IsMemWrite  = (MemWrite & ~IsIO) ? 1 : 0;         // 1: if MemWrite and not IsIO, we write to memory, not to the IO
   assign IOWriteData = WriteData;                          // This line is connected directly to WriteData
   assign IOAddr      = ALUResult[3:0];                     // The LSB 4 bits of the Address is assigned to IOAddr, [3:0]
   assign IOWriteEn   = MemWrite & IsIO;                    // Is 1 when there is a SW instruction on IO address 

   assign ReadMemIO   = IsIO ? IOReadData : ReadData;   // Mux selects memory or I/O	
   assign Result = MemtoReg ? ReadMemIO : ALUResult;    // Select either the Data Memory (or IO) output or the ALU Result	

   ////////////////////////////////////
   // The Control Unit
   ControlUnit i_cont (
      // TODO PART 1 !!
      .Op(Instr[31:26]),
      .Funct(Instr[5:0]),
      .Jump(Jump),
      .MemtoReg(MemtoReg),
      .MemWrite(MemWrite),
      .Branch(Branch),
      .ALUControl(ALUControl),
      .ALUSrc(ALUSrc),
      .RegDst(RegDst),
      .RegWrite(RegWrite)
   );
endmodule




///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

module ControlUnit(
	input  [5:0] Op,
	input  [5:0] Funct,
	output       Jump,
	output       MemtoReg,
	output       MemWrite,
	output       Branch,
	output [5:0] ALUControl,
	output       ALUSrc,
	output       RegDst,
	output       RegWrite
   );
	 
	//////////////////////////////////////////////////////////////////////////////////
	// DEFINE SOME CONSTANTS to make life easier
	localparam [5:0] OP_RTYPE = 6'b000000;  // There is an R-Type of Operation
	localparam [5:0] OP_LW    = 6'b100011;  // The Load Word operation
	localparam [5:0] OP_SW    = 6'b101011;  // The Store Word operation
	localparam [5:0] OP_BEQ   = 6'b000100;  // The Branch on Equal operation
	localparam [5:0] OP_ADDI  = 6'b001000;  // The ADDimmediate operation
	localparam [5:0] OP_J     = 6'b000010;  // The Jump operation

	//////////////////////////////////////////////////////////////////////////////////
	// DEFINE THE MAIN CONTROL SIGNALS
	// The control signals are mostly based on the table 7.5 on page 379
	// Don't care statements have been mapped to '0' in most cases
	//
	// All signals are handled separately, see alternative for an example

	// We will write to registers when OP is Rtype or LW or ADDI 
	assign RegWrite = (Op == OP_RTYPE) | (Op == OP_LW) | ( Op == OP_ADDI) ; 	 
	// Select the input of the ALU
	assign ALUSrc   = (Op == OP_LW) | (Op == OP_SW) | (Op == OP_ADDI) ;

	// Simple assignments
	assign RegDst   = (Op == OP_RTYPE); // The destination is a register
	assign Branch   = (Op == OP_BEQ);   // 1: if there is a branch instruction 
	assign MemWrite = (Op == OP_SW);    // 1: for Store Word
	assign MemtoReg = (Op == OP_LW);	   // 1: when Load Word
	assign Jump     = (Op == OP_J);	   // 1: when Jump

	assign ALUControl =
		ALUSrc ? 6'b100000 :  // if LW, SW or ADDI, perform an ADD
		Branch ? 6'b100010 :  // if BEQ, perform a SUB
		Funct;       // per default assume an R-Type and do what Funct says
endmodule

module InstructionMemory(
  input  [5:0] A,   // Address of the Instruction max 64 instructions
	output [31:0] RD   // Value at Address
  );

 reg [31:0] InsArr [63:0];  // Array holding the memory 64 entries each 32 bits
 
 initial
  begin
    $readmemh("insmem_h.txt", InsArr);  // Initialize the array with this content
  end
  assign RD= InsArr[A];   // The Read Data (RD) output corresponds to the Address (A)
endmodule

module DataMemory(
  input         CLK,  // Clock signal rising edge
  input   [5:0] A,    // Address for 64 locations
  input         WE,   // Write Enable 1: Write 0: no write
  input  [31:0] WD,   // 32-bit data in
  output [31:0] RD    // 32-bit read data
  );
  reg [31:0] DataArr [63:0];   // This is the variable that holds the memory
  initial
  begin
    $readmemh("datamem_h.txt", DataArr);  // Initialize the array with this content
  end

  assign RD = DataArr[A];      // Read Data (RD) corresponds to address (A)

  always @ ( posedge CLK )     // At rising edge of CLK
  begin
    if (WE)                  // if Write Enable (WE) is set
      DataArr[A] <= WD;     // Copy Write Data (WD) to the address (A)
  end
endmodule

module ALU ( 
  input  [31:0] a,
  input  [31:0] b,
	input  [3:0] aluop,
	output [31:0] result,
	output zero
  );

  wire [31:0] logicout;   // output of the logic block
  wire [31:0] addout;     // adder subtractor out
  wire [31:0] arithout;   // output after alt
  wire [31:0] n_b;        // inverted b
  wire [31:0] sel_b;      // select b or n_b;
  wire [31:0] slt;        // output of the slt extension
  
  wire [1:0] logicsel;    // lower two bits of aluop;

  // logic select 
  assign logicsel = aluop[1:0];
  assign logicout =
    (logicsel == 2'b00) ? a & b :
    (logicsel == 2'b01) ? a | b :
    (logicsel == 2'b10) ? a ^ b :
    ~(a | b) ;

  // adder subtractor
  assign n_b = ~b ;  // invert b
  assign sel_b = (aluop[1])? n_b : b ;
  assign addout = a + sel_b + aluop[1];
  
  // set less than operator
  assign slt = {31'b0,addout[31]};
  
  // arith out
  assign arithout = (aluop[3]) ? slt : addout;
  
  // final out
  assign result = (aluop[2]) ? logicout : arithout;
  
  // the zero
  assign zero = (result == 32'b0) ? 1: 0;
endmodule

module reg_half(
	a,
	d,
	dpra,
	clk,
	we,
	spo,
	dpo);

	input [4 : 0] a;
	input [31 : 0] d;
	input [4 : 0] dpra;
	input clk;
	input we;
	output [31 : 0] spo;
	output [31 : 0] dpo;

   // synthesis translate_off

   //    DIST_MEM_GEN_V4_1 #(
	// 	.C_ADDR_WIDTH(5),
	// 	.C_DEFAULT_DATA("0"),
	// 	.C_DEPTH(32),
	// 	.C_HAS_CLK(1),
	// 	.C_HAS_D(1),
	// 	.C_HAS_DPO(1),
	// 	.C_HAS_DPRA(1),
	// 	.C_HAS_I_CE(0),
	// 	.C_HAS_QDPO(0),
	// 	.C_HAS_QDPO_CE(0),
	// 	.C_HAS_QDPO_CLK(0),
	// 	.C_HAS_QDPO_RST(0),
	// 	.C_HAS_QDPO_SRST(0),
	// 	.C_HAS_QSPO(0),
	// 	.C_HAS_QSPO_CE(0),
	// 	.C_HAS_QSPO_RST(0),
	// 	.C_HAS_QSPO_SRST(0),
	// 	.C_HAS_SPO(1),
	// 	.C_HAS_SPRA(0),
	// 	.C_HAS_WE(1),
	// 	.C_MEM_INIT_FILE("no_coe_file_loaded"),
	// 	.C_MEM_TYPE(2),
	// 	.C_PARSER_TYPE(1),
	// 	.C_PIPELINE_STAGES(0),
	// 	.C_QCE_JOINED(0),
	// 	.C_QUALIFY_WE(0),
	// 	.C_READ_MIF(0),
	// 	.C_REG_A_D_INPUTS(0),
	// 	.C_REG_DPRA_INPUT(0),
	// 	.C_SYNC_ENABLE(1),
	// 	.C_WIDTH(32))
	// inst (
	// 	.A(a),
	// 	.D(d),
	// 	.DPRA(dpra),
	// 	.CLK(clk),
	// 	.WE(we),
	// 	.SPO(spo),
	// 	.DPO(dpo),
	// 	.SPRA(),
	// 	.I_CE(),
	// 	.QSPO_CE(),
	// 	.QDPO_CE(),
	// 	.QDPO_CLK(),
	// 	.QSPO_RST(),
	// 	.QDPO_RST(),
	// 	.QSPO_SRST(),
	// 	.QDPO_SRST(),
	// 	.QSPO(),
	// 	.QDPO());

   // synthesis translate_on
   // XST black box declaration
   // box_type "black_box"
   // synthesis attribute box_type of reg_half is "black_box"
endmodule

module RegisterFile(
	input   [4:0] A1,   // selects one of 32 registers
	output [31:0] RD1,  // register corresponding to A1
	input   [4:0] A2,   // selects one of 32 registers
	output [31:0] RD2,  // register corresponding to A2
	input   [4:0] A3,   // selects the address for writeback
	input  [31:0] WD3,  // Write-back data, will be written to addess A3
	input         WE3,  // Write-enable for third port WE3=1 write WD3 to A3
	input         CLK   // System clock
   );
   
   wire [31:0] Read1;  // output of PortA
	wire [31:0] Read2;  // output of PortB
	// once everything works do not forget to add a reset here 
	// you should not enable WE while there is a reset
 
  	// we instantiate two memories  
	reg_half i_portA	 (
	   .a(A3), // Bus [4 : 0] 
	   .d(WD3), // Bus [31 : 0] 
	   .dpra(A1), // Bus [4 : 0] 
	   .clk(CLK),
	   .we(WE3),
	   .dpo(Read1)
	); // Bus [31 : 0] 

	reg_half i_portB (
	   .a(A3), // Bus [4 : 0] 
	   .d(WD3), // Bus [31 : 0] 
	   .dpra(A2), // Bus [4 : 0] 
	   .clk(CLK),
	   .we(WE3),
	   .dpo(Read2)
	); // Bus [31 : 0]     
	
	// For both ports, if the address is 0 
	// Then the output will be zero
	// Otherwise read the output from the register file 

	assign RD1 = (A1 != 4'b0000) ? Read1 : 0; // Port 1
	assign RD2 = (A2 != 4'b0000) ? Read2 : 0; // Port 2
endmodule
