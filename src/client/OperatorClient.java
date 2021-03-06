package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import cliente.conn.MainServerConnection;
import base.conn.ServerConnection;
import base.operator.OperatorType;

/** 
 * Cliente
 * 
 */
public class OperatorClient
{
	private List<Double> params = null;
	private OperatorType operatorType = null;

	private ServerConnection serverConnection = null;

	public OperatorClient(List<Double> params, OperatorType operatorType) 
	{
		this.params = params;
		this.operatorType = operatorType;
	}

	/** 
	 * L� o resultado da opera��o 
	 * @return Valor da opera��o  
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws JDOMException 
	 */  
	public Double getValue() throws FileNotFoundException, IOException, JDOMException 
	{
		if (!isParamsValid()) 
		{
			throw new IllegalArgumentException("Par�metros inv�lidos!");
		}

		serverConnection = new MainServerConnection("XMLClient.properties");
		serverConnection.open();
		Document serverDoc = params2XMLDocument();

		serverConnection.sendDocumentToServer(serverDoc);
		Document clientDoc = serverConnection.getDocumentFromServer();
		Double returnValue = getValueFromDoc(clientDoc);
		return returnValue;
	}

	/** 
	 * L� os par�metros e gera o documento XML que ser� enviado ao servidor  
	 * principal 
	 * @return Documento XML, baseado nos par�metros e no tipo da opera��o 
	 */  
	private Document params2XMLDocument() 
	{
		Document doc = new Document();
		Element root = new Element("operation");

		for (Double paramValue: params) 
		{
			Element param = new Element("param");
			param.addContent(paramValue.toString());
			root.addContent(param);
		}
		Element type = new Element("type");
		type.addContent(operatorType.toString());
		root.addContent(type);
		doc.setRootElement(root);

		return doc;
	}

	/** 
	 * Verifica se � poss�vel realizar o c�lculo com os argumentos 
	 * @return verdadeiro se for poss�vel realizar o c�lculo, falso caso contr�rio  
	 */  
	private boolean isParamsValid() 
	{
		return this.params != null && this.params.size() > 0   
				&& this.operatorType != null;
	}

	/** 
	 * L� o valor (resultado) que veio do documento XML do servidor principal 
	 * @param doc Documento XML que veio do servidor principal 
	 * @return 
	 */  
	private Double getValueFromDoc(Document doc) 
	{
		Element root = doc.getRootElement();
		Element value = root.getChild("value");
		Double returnValue = Double.parseDouble(value.getValue());
		return returnValue;
	}
}
