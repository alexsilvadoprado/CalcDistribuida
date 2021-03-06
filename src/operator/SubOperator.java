package operator;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * 
 * Efetua a subtra��o
 *
 */
public class SubOperator implements Operator
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
		System.out.println("Efetuando opera��o de subtra��o...");
		
		Element root      = doc.getRootElement();
		
		List<Element> children = root.getChildren();
		Element minuendo =  children.get(0);
		Element subtraendo   =  children.get(1);
		  
        Double sub = Double.parseDouble(minuendo.getValue()) - Double.parseDouble(subtraendo.getValue());
          
        Element result = new Element("result");
        Element value  = new Element("value");
          
        value.addContent(String.valueOf(sub));
        result.addContent(value);
          
        Document doc = new Document();
        doc.setRootElement(result);
        return doc;
	}

	@Override
	public String getOperatorName()
	{
		return "Subtra��o";
	}
	
}
