package operator;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * 
 * Efetua a divis�o
 *
 */
public class DivOperator implements Operator
{
	private Document doc = null;
	
	@Override
	public void setSourceDocument(Document doc)
	{
		this.doc = doc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Document getTargetDocument()
	{
		System.out.println("Efetuando opera��o de divis�o...");
        
        Element root      = doc.getRootElement();
          
        List<Element> children = root.getChildren();
        Element dividendo =  children.get(0);
        Element divisor   =  children.get(1);
  
        Double div = Double.parseDouble(dividendo.getValue()) / Double.parseDouble(divisor.getValue());
          
        Element result = new Element("result");  
        Element value  = new Element("value");
          
        value.addContent(String.valueOf(div));
        result.addContent(value);
          
        Document doc = new Document();
        doc.setRootElement(result);
        return doc;
	}

	@Override
	public String getOperatorName()
	{
		return "Divis�o";
	}
}
