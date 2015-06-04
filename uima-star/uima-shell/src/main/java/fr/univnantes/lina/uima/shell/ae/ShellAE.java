/** 
 * UIMA Shell AE 
 * Copyright (C) 2010  Nicolas Hernandez
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.univnantes.lina.uima.shell.ae;


import fr.univnantes.lina.javautil.CollectionUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.UIMAUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas; //
import org.apache.uima.resource.ResourceInitializationException; 
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.developpez.adiguba.shell.Shell;



/**
 * UIMA Shell wrapper Analysis Engine
 * @author Nicolas Hernandez
 */
public class ShellAE extends CommonAE {

	
	/** Specific component parameters in descriptor file */
	public static String PARAM_NAME_ENVVAR = "EnvironmentVariables";
	public static String PARAM_NAME_WORKINGDIR = "WorkingDirectory";
	public static String PARAM_NAME_CHARSET = "CharsetName";
	public static String PARAM_NAME_PRECMDTOKENS = "PreCommandTokens";
	public static String PARAM_NAME_POSTCMDTOKENS = "PostCommandTokens";

	
	/** Specific component variable */
	private String[] envVarStringArray = null;
	private String workingDirString = null;
	private File workingDirFile  = null;
	private String charsetNameString = null;
	private String[] preCmdTokensStringArray  = null;
	private String[] postCmdTokensStringArray  = null;
	private String[] cmdArrayStringArray  = null;


	/**
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		super.initialize(aContext);


		
		envVarStringArray = (String[]) aContext.getConfigParameterValue(PARAM_NAME_ENVVAR);
		if (envVarStringArray.length  == 0 ) {envVarStringArray = null;}

		workingDirString = (String) aContext.getConfigParameterValue(PARAM_NAME_WORKINGDIR);
		if (workingDirString != null ) 
		{workingDirFile = new java.io.File (workingDirString);}

		charsetNameString = (String) aContext.getConfigParameterValue(PARAM_NAME_CHARSET);

		preCmdTokensStringArray = (String[]) aContext.getConfigParameterValue(PARAM_NAME_PRECMDTOKENS);
		postCmdTokensStringArray = (String[]) aContext.getConfigParameterValue(PARAM_NAME_POSTCMDTOKENS);
		
	}


	

	/**
	 * Perform a shell command
	 *  
	 * @param aJCas
	 * @param inputTextToProcess
	 * @param beginFeatureValue
	 * @param endFeatureValue
	 * 
	 * @return the sdtout result 
	 * @throws AnalysisEngineProcessException 
	 */
	public String processAnnotationFeatureStringValue (JCas aJCas, String inputTextToProcess, int beginFeatureValue, int endFeatureValue) throws AnalysisEngineProcessException {

		//System.out.println("Debug: ShellAE processAnnotationFeatureStringValue");
		//preCmdTokensStringArray, postCmdTokensStringArray,charsetNameString, workingDirString, envVarStringArray);
		/** -- Prepare the command **/
		log("Building the command");
		String cmdString = buildTheCommand(preCmdTokensStringArray,inputTextToProcess, postCmdTokensStringArray);

		/** -- Execute and get result **/
		log("Executing the command >"+cmdString+"<");
		String commandLocalResultString = "";
		commandLocalResultString = command(cmdString, charsetNameString, workingDirString, envVarStringArray);
		
		return commandLocalResultString;
		
	}
	
	
	
	
	/**
	 * Crée un processus représentant la commande du shell et l'associe à une instance de ProcessConsumer
	 * @param cmdString
	 * @param charsetNameString
	 * @param workingDirString
	 * @param envVarStringArray
	 * @return commandResultString
	 * @throws AnalysisEngineProcessException
	 */
	private String command(String cmdString, String charsetNameString, String workingDirString, String [] envVarStringArray)
	throws AnalysisEngineProcessException {
		String commandResultString = null;
		Shell sh = new Shell(); 

		/** Etat de l'environnement d'exécution du shell **/
		// Retourne le nom du shell système.
		//log("Debug: nom du shell système >"+sh.toString()+"<");
		// Retourne le charset associé avec cette instance de shell. 
		//log("Debug: charset du shell>"+sh.getCharset().toString()+"<");
		// Retourne le répertoire à partir duquel les commandes du shell seront lancés. 
		//log("Debug: repertoire d'exécution du shell>"+sh.getDirectory().getAbsolutePath()+"<");
		// Retourne une map contenant les variables d'environnements utilisateurs. Cette Map est librement modifiables afin d'ajouter/supprimer des éléments.

		Map<String,String> varUserEnvMap = sh.getUserEnv();
		//if (envVarStringArray != null) log("Debug: envVar.length"+envVarStringArray.length);	

		// Indique si les variables d'environnements de l'application Java courante doivent être passé aux commandes lancées par ce shell.
		//if (sh.isSystemEnvInherited()) {
		//	log("Debug: les variables d'environnements de l'application Java courante sont passées aux commandes lancées par ce shell");	
		//}

		// Déclaration au sein du shell des var d'env définies en paramètres
		if (envVarStringArray != null)
			for(int i = 0; i < envVarStringArray.length; i++){
				int firstEqualPos = envVarStringArray[i].indexOf("=");
				String key = envVarStringArray[i].substring(0, firstEqualPos);
				String value = envVarStringArray[i].substring(firstEqualPos+1, envVarStringArray[i].length());
				//log("Debug: var param"+key+"="+value);	
				varUserEnvMap.put(key, value);
			}

		if (varUserEnvMap.isEmpty()) {
			//log("Debug: pas de variables d'environnement définies par paramètre");
		}
		else {
			Iterator<String> varUserEnvIter = varUserEnvMap.keySet().iterator();
			while (varUserEnvIter.hasNext()) {
				String key = (String) varUserEnvIter.next();
				String value = (String) varUserEnvMap.get(key);
				//log("Debug: user env var "+key+"="+value);	
			}
		}	

		// Modifie le charset associé avec cette instance de shell.
		if (charsetNameString != null)  sh.setCharset(charsetNameString);			
		// Modifie le répertoire à partir duquel les commandes du shell seront lancés.
		if (workingDirString != null ) sh.setDirectory(workingDirFile);


		// Crée un processus représentant la commande du shell et l'associe à une instance de ProcessConsumer
		try {
			commandResultString = sh.command(cmdString).consumeAsString();
		}
		catch (IllegalStateException e) {
			String errmsg = "ERROR: IllegalStateException with sh.command(cmdString).consumeAsString() !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);
			//e.printStackTrace();
		} catch (IOException e) {
			String errmsg = "ERROR: IOException with sh.command(cmdString).consumeAsString() !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);
			//e.printStackTrace();
		}
		return commandResultString;
	}

	/**
	 * Build the command 
	 * @param preCmdTokensStringArray
	 * @param dataString
	 * @param postCmdTokensStringArray
	 * @return cmdString
	 * @throws AnalysisEngineProcessException 
	 */
	private String buildTheCommand(String[] preCmdTokensStringArray, String dataString, String[] postCmdTokensStringArray) throws AnalysisEngineProcessException {
		log("Creating a temporary file containing the dataString to proceed");
		String[] dataStringArray = new String[1]; 
		dataStringArray[0] = UIMAUtils.createAETempTextFile (getClass().getSimpleName() + "_tmp_", ".bak", dataString);

		String[] tmp = CollectionUtilities.concat(preCmdTokensStringArray, dataStringArray);
		cmdArrayStringArray = CollectionUtilities.concat (tmp, postCmdTokensStringArray);
		String cmdString = "" ;
		for(int i = 0; i < cmdArrayStringArray.length; i++){
			cmdString = cmdString + " " + cmdArrayStringArray[i];
		}
		return cmdString;
	}




}