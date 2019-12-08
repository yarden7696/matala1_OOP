package myMath;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation operation;
	
	public ComplexFunction(Operation operation,function left, function right)
	{
		this.left = left;
		this.right = right;
		this.operation = operation;
	}
	
	
	
	public ComplexFunction(function f)
	{
		this.left = f;
		this.right = null;
		this.operation = Operation.None;	
	}
	
	public ComplexFunction(String operation,function left, function right)
	{	
		this.left = left;
		this.right = right;
		this.operation=Operation.valueOf(operation);
	}

		
	@Override
	public double f(double x) {
		
		String  opera = this.operation.name();
		switch (opera)
		{
		case "Plus" :
			return this.left().f(x)+ this.right().f(x);
		
		case "Times" :
			return this.left().f(x) * this.right().f(x);
			
		case "Divid" :
			return this.left().f(x) / this.right().f(x);
			
		case "Max" :
		if (this.left().f(x) > this.right().f(x))	{return this.left().f(x);}
		else
		{
			return this.right().f(x);
		}
			
		case "Min" :
			if (this.left().f(x) < this.right().f(x)) {return this.left().f(x);}
			else 
			{
				return this.right().f(x);
			}
					
		case "Comp" :
			return this.left().f( this.right().f(x));
			
		case "None" :
			return this.left().f(x);
			
		 default: //case "Error"
             throw new RuntimeException("Illegal Operation");
		
		}
		
		
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		
		ComplexFunction temp;
		if(this.right== null)
		{
			temp= new ComplexFunction (left);
		}
		else
		{
			temp =  new ComplexFunction (this.operation.name(), this.left(),this.right());
		}
		return temp ;
	}

	@Override
	public void plus(function f1) {
	
		ComplexFunction temp;
		if(this.right()!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Plus"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Plus"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}
	}

	@Override
	public void mul(function f1) {
		

		ComplexFunction temp;
		if(this.right!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Times"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Times"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}	
	}

	@Override
	public void div(function f1) {

		ComplexFunction temp;
		if(this.right!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Divid"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Divid"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}
	}

	@Override
	public void max(function f1) {
		
		ComplexFunction temp;
		if(this.right!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Max"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Max"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}
	}

	@Override
	public void min(function f1) {
			
		ComplexFunction temp;
		if(this.right!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Min"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Min"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}
	}

	@Override
	public void comp(function f1) {
		
		ComplexFunction temp;
		if(this.right!= null)
		{
		 temp = new ComplexFunction (this.operation,this.left,this.right);
		 	
		 this.setLeft(temp);
		 this.setRight(f1);
		 this.setOp(Operation.valueOf("Comp"));
		}
		else
		{
			temp = new ComplexFunction (this);
			temp.setRight(f1);
			temp.setOp(Operation.valueOf("Comp"));
			this.setLeft(temp.left());
			this.setRight(temp.right());
			this.setOp(temp.getOp());
		}
		
	}

	@Override
	public function left() {
	
		return this.left;
	}
	

	@Override
	public function right() 
	{
	return this.right;
	}
    
	
	public void setRight(function f) 
	{
		this.right= f;
	}
	
	
	public void setLeft(function f) 
	{
		this.left= f;
	}
	
	
	public void setOp(Operation op) 
	{
		this.operation= op;
	}
	
	
	@Override
	public Operation getOp() 
	{
		return this.operation;
	}
   
	
	public String toString() {
		//return this.getOp()+"("+this.left+","+this.right+")";
		
	}
	
	
	public static void main(String[] args) {

		Polynom p3 = new Polynom("x+3");
		ComplexFunction cf3 = new ComplexFunction(p3);
		ComplexFunction cf4 = new ComplexFunction("Plus", p3,p3);  
		cf3.plus(cf4);
		System.out.println(cf3);
		//cf4.plus(cf4);
		//cf4.mul(cf4);
		//cf4.div(cf4);
		//cf4.min(cf4);
		//cf4.max(cf4);
	//	System.out.println(cf4);
	
	}
	
}