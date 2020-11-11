package operator;

import org.jdom2.Document;

/**
 * 
 * Interface que será implementada pelos operadores
 *
 * @since 20/04/2015 14:43:08
 * @version 1.0
 */
public interface Operator
{
	/**
	 * 
	 * Seta o documento XML que será usado no cálculo
	 * @param params Map com os parâmetros
	 * 
	 */
	void setSourceDocument(Document doc);
	
	/**
	 * 
	 * Efetua o cálculo e retorna o documento XML gerado
	 * @return Documento XML produzido com o resultado da operação
	 * 
	 */
	Document getTargetDocument();
	
	/**
	 * 
	 * Método responsável por
	 * @return Uma String contendo o nome do operador que está sendo usado
	 * 
	 */
	String getOperatorName();
}
