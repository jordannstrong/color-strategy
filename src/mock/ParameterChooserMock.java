package mock;

import java.lang.reflect.Parameter;
import java.util.LinkedList;

/* This class will serve as the User Interface for 
* selecting which flights the user wishes to visualize
* via matching Parameters.
*@author Spencer Bialt
*@date 04/10/2016 
*/
public class ParameterChooserMock
{
	private LinkedList<Parameter> parameterList;

	//Must be initialized with the 
	protected ParameterChooserMock(LinkedList<Parameter> _parameterList)
	{
		//Prepare parameter list for iteration
		
	}

	/*Class will return the list of columns chosen by the user
	*/
	public LinkedList<Parameter> getParameterList()
	{
		//Must add logic to determine what is currently checked off 
		return parameterList;
	}
}
