package operator;

import org.jdom2.Document;

/**
 * 
 * Interface que ser� implementada pelos operadores
 *
 * @since 20/04/2015 14:43:08
 * @version 1.0
 */
public interface Operator
{
	/**
	 * 
	 * Seta o documento XML que ser� usado no c�lculo
	 * @param params Map com os par�metros
	 * 
	 */
	void setSourceDocument(Document doc);
	
	/**
	 * 
	 * Efetua o c�lculo e retorna o documento XML gerado
	 * @return Documento XML produzido com o resultado da opera��o
	 * 
	 */
	Document getTargetDocument();
	
	/**
	 * 
	 * M�todo respons�vel por
	 * @return Uma String contendo o nome do operador que est� sendo usado
	 * 
	 */
	String getOperatorName();
}
