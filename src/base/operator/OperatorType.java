package base.operator;

/**
 * 
 * Define os tipos de operação existentes
 *
 * @since 20/04/2015 14:21:45
 * @version 1.0
 */
public enum OperatorType
{
	ADD("operator.AddOperator"),
	SUB("operator.SubOperator"),
	MUL("operator.MulOperator"),
	DIV("operator.DivOperator");
	
	private String className = null;
	
	private OperatorType(String className)
	{
		this.className = className;
	}
	
	public String getClassName()
	{
		return this.className;
	}
}
