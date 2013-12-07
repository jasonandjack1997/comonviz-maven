package au.uq.dke.comonviz.utils;

import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.misc.CustomRuntimeException;
import database.model.ontology.OntologyClass;

public class StringUtils {

	
	public static String getTableNameByNodeName(String nodeName) {
		String tableName = nodeName.replace(" of ", "Of").replace(" ", "").replace("&", "And").replace("-", "");
		return tableName;
	}
	
	public static String getClassUserFriendlyName(String className){
		String userFriendlyName = className.replaceAll("([a-z])([A-Z][a-z])", "$1 $2").replaceAll("And", "&");
		return userFriendlyName;
	}

	
	public static void main(String[] args) {
		getClassUserFriendlyName("RiskIdentificationAndAssessment");
	}
}
