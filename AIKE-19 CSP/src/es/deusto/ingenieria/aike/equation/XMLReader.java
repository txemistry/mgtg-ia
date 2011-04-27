package es.deusto.ingenieria.aike.equation;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class XMLReader extends InformationXMLReader 
{

	/**
	 * @uml.property  name="myParameters"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.Integer"
	 */
	private List<Integer> myParameters;
	
	public XMLReader(String xmlFile) 
	{
		super(xmlFile);
	}
	
	public Object getInformation() 
	{
		
		return this.myParameters;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		
		try 
		{		
			if(qName.equals("aike:equation"))
			{
				myParameters = new ArrayList<Integer>();
				myParameters.add(Integer.parseInt(attributes.getValue("multiplier")));
				myParameters.add(Integer.parseInt(attributes.getValue("constant")));
				myParameters.add(Integer.parseInt(attributes.getValue("maxMinutes")));
			}
		} 
		catch (Exception ex) 
		{
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
	}
}