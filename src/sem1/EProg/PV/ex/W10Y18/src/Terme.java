interface Expr {
	Expr[] children();
	int eval();
}

interface BinOp extends Expr {
	Expr left();

	Expr right();
}

interface UnaryOp extends Expr {
	Expr operand();
}

interface Constant extends Expr {
	int val();
}

