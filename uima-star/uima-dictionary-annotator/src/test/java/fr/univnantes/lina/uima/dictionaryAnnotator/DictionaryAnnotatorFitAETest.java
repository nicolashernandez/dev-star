/**
 * 
 */
package fr.univnantes.lina.uima.dictionaryAnnotator;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ExternalResourceDescription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import fr.univnantes.lina.uima.common.resources.CSVDictionaryResource;

/**
 * @author hernandez-n
 *
 */
public class DictionaryAnnotatorFitAETest {


	//http://www.ubuntu-fr.org/ubuntu/philosophie
	final private static String ubuntufrphilosophie = "Notre travail sur Ubuntu est inspiré par une philosophie de la liberté des logiciels qui, espérons nous, s'épandra et apportera les bénéfices de la technologie informatique de par le monde."
			+"Logiciel libre et gratuit "
			+"Ubuntu est un projet communautaire de création d'un système d'exploitation et d'un ensemble complet de programmes informatiques utilisant des logiciels libres et gratuits. Au coeur de la Philosophie d'Ubuntu sur la Liberté Logicielle se trouvent ces idéaux philosophiques : "
			+"Tout utilisateur d'ordinateur devrait avoir la liberté d'exécuter, de copier, de distribuer, d'étudier, de partager, de modifier et d'améliorer ses logiciels pour toutes fins, sans payer aucun frais de licence. "
			+"Tout utilisateur d'ordinateur devrait pouvoir utiliser ses logiciels dans la langue de son choix. "
			+"Tout utilisateur d'ordinateur devrait être en mesure d'utiliser tous ses logiciels, même s'il souffre d'un handicap. "
			+"Notre philosophie est reflétée dans les programmes que nous produisons et incluons dans notre distribution. De ce fait, les termes des licences des logiciels que nous distribuons sont en accord avec notre philosophie, selon la Politique de Licence d'Ubuntu. "
			+"Quand vous installez Ubuntu, presque tous les programmes installés respectent déjà ces idéaux, et nous travaillons dans le but que chacune des pièces composant Ubuntu dont vous avez besoin soit disponible sous une licence qui vous assure ces libertés. Actuellement, nous appliquons une exception bien spécifique pour quelques pilotes qui ne sont disponibles uniquement que sous forme binaire, sans quoi de nombreux ordinateurs ne sauraient compléter correctement le processus d'installation d'Ubuntu. Nous les plaçons dans une section restreinte de votre système, ce qui les rend trivials à supprimer si vous n'en avez pas besoin. "
			+"Pour plus d'informations à propos des composants d'Ubuntu, veuillez visiter Components. "
			+"Logiciel libre "
			+"La notion la plus importante propos d'Ubuntu n'est pas qu'elle est distribuée gratuitement, même si nous nous engageons ne jamais charger le moindre centime pour Ubuntu. C'est plutôt qu'elle confère le droit aux libertés logicielles aux personnes installant et utilisant Ubuntu. Ce sont ces libertés qui permettent la communauté d'Ubuntu de s'agrandir, d'échanger son expérience collective et son expertise pour améliorer Ubuntu et la rendre utilisable dans de nouveaux pays et de nouvelles industries. "
			+"Tirés de What is Free Software de la Free Software Foundation, les libertés la base du concept des logiciels libres sont définies ainsi : "
			+"La liberté d'exécuter un programme, pour quelque fin que ce soit. "
			+"La liberté d'étudier le fonctionnement du programme et de l'adapter ses propres besoins. "
			+"La liberté de redistribuer des copies d'un programme, pour que vous aidiez votre prochain. "
			+"La liberté d'améliorer un programme et de publier vos améliorations au public, afin d'en faire bénéficier tout le monde. "
			+"Le Logiciel Libre a été un mouvement social cohérent depuis plus de deux décennies. Ce mouvement a produit des millions de lignes de code et de documentation, ainsi qu'une communauté vivante, active laquelle Ubuntu est fière de faire partie. "
			+"Logiciel code ouvert "
			+"Le terme code ouvert (open source) est apparu en 1998 afin de supprimer l'ambiguïté entourant le mot anglais free (qui signifie aussi bien gratuit que libre). L'Open Source Initiative décrit le logiciel code source ouvert dans sa Définition du Logiciel Code Ouvert. Le mouvement du code ouvert continue de jouir d'un succès grandissant et d'une reconnaissance mondiale. "
			+"Ubuntu est heureuse se qualifier code ouvert. Alors que certains préfèrent voir le code ouvert et le logiciel libre comme deux mouvements en compétition et buts différents, nous nous ne voyons pas ces mouvements comme étant distincts ou incompatibles. Ubuntu comprend fièrement des membres s'identifiant autant au mouvement du logiciel libre que du mouvement du code ouvert, ainsi que de nombreux s'attachant aux deux camps."; 




	/**
	 * Demonstration of how to use the dictionaryAnnotatorFitAE
	 * 
	 * 
	 * @throws UIMAException
	 * @throws IOException
	 */
	@Test
	public void demonstrateDefaultDictionaryAnnotatorFitAEUse() throws UIMAException, IOException {

		// Creation of the external resource description
		ExternalResourceDescription verbDicRD = createExternalResourceDescription(CSVDictionaryResource.class,
				"file:dictionaryAnnotator/Verb.dic");

		//ExternalResourceDescription ircChannelResDesc = createExternalResourceDescription(IrcChannel_impl.class,
		//		new File("pipo.bin"));


		// Binding external resource to each Annotator individually

		//AnalysisEngineDescription aed0 = createEngineDescription(IrcParserAE.class,
		//		IrcParserAE.IRC_CHANNEL_RES_KEY, ircChannelResDesc);

		AnalysisEngineDescription dictionaryAnnotatorFitAE = createEngineDescription(DictionaryAnnotatorFitAE.class, 
				DictionaryAnnotatorFitAE.RESOURCE_CSV_DICT, verbDicRD,
				DictionaryAnnotatorFitAE.PARAM_OUTPUT_ANNOTATION, "fr.univnantes.lina.uima.dictionaryAnnotator.DictionaryAnnotation");

		// Check the external resource was injected
		AnalysisEngine ae = createEngine(dictionaryAnnotatorFitAE);


		// Creation of the jcas to process
		JCas jcas = JCasFactory.createJCas(); //ae.newJCas();
		jcas.setDocumentText(ubuntufrphilosophie);
		//DocumentAnnotation aDocumentAnnotation = new DocumentAnnotation(jcas);
		//aDocumentAnnotation.setBegin(0);
		//aDocumentAnnotation.setEnd(ubuntufrphilosophie.length());
		//aDocumentAnnotation.addToIndexes(jcas);
		//	        jcas.setDocumentLanguage("en");


		// Run the pipeline
		//SimplePipeline.runPipeline(jcas,dictionaryAnnotatorFitAE);
		ae.process(jcas);

		for (DictionaryAnnotation aDictionaryAnnotation : select(jcas, DictionaryAnnotation.class)) {
			System.out.printf("%s\n", aDictionaryAnnotation.getCoveredText());
		}


	}

	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}


}
