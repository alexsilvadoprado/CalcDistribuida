package mainServer;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import mainServer.conn.OperatorServerConnection;

import org.jdom2.Document;
import org.jdom2.Element;

import base.conn.ServerConnection;
import base.operator.OperatorType;
import base.xml.XMLDocumentIO;

public class MainRunner implements Runnable
{
	private XMLDocumentIO xmlDocumentIO = null;

	public MainRunner(Socket socket) throws IOException 
	{
		xmlDocumentIO = new XMLDocumentIO(socket);
	}

	@Override
	public void run()
	{
		try 
		{  
			Document clientDoc = xmlDocumentIO.getXMLDocument();
			Document serverDoc = clientDoc2ServerDoc(clientDoc);

			OperatorType operatorType = getOperatorType(clientDoc);

			ServerConnection con = new OperatorServerConnection("XMLMainServer.properties", operatorType);
			con.open();
			con.sendDocumentToServer(serverDoc);

			Document resultDoc = con.getDocumentFromServer();

			xmlDocumentIO.sendXMLDocument(resultDoc);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/** 
	 * Descobre qual o tipo de opera��o solicitada pelo cliente 
	 * @param doc Documento XML que veio do cliente 
	 * @return 
	 */  
	private OperatorType getOperatorType(Document doc) 
	{
		System.out.println("Descobrindo o tipo da operacao...");
		Element root = doc.getRootElement();
		Element type = root.getChild("type");
		return OperatorType.valueOf(type.getValue());
	}

	/** 
	 * Converte o documento XML que veio do cliente para o documento XML 
	 * que ser� enviado ao servidor de opera��o 
	 * @param clientDoc Documento XML recebido do cliente 
	 * @return Documento XML que ser� enviado ao servidor de opera��o 
	 */  
	@SuppressWarnings("unchecked")  
	private Document clientDoc2ServerDoc(Document clientDoc) 
	{
		Document docServer  = new Document();
		Element  rootServer = new Element("operation");

		Element rootClient = clientDoc.getRootElement();

		List<Element> children = rootClient.getChildren();

		for (Element chield : children) 
		{
			if (chield.getName().equalsIgnoreCase("param")) 
			{
				Element param = new Element("param");
				param.addContent(chield.getValue());
				rootServer.addContent(param);
			}
		}

		docServer.setRootElement(rootServer);
		return docServer;
	}
}
