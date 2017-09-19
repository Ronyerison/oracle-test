/**
 * 
 */
package br.ufpi.loes.oracleTest.core.machineLearning;

import java.io.File;
import java.util.Random;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.Utils;
import weka.experiment.InstanceQuery;

/**
 * @author Rony
 *
 */
public class MachineLearning {

	private Instances instances;
	private Classifier classifier;
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PASSWORD = "root";

	public void inicializeInstances() {
		try {
			File file = new File(getClass().getClassLoader().getResource("DatabaseUtils.props").toURI());
			InstanceQuery query = new InstanceQuery();
			query.setCustomPropsFile(file);
			query.setUsername(MYSQL_USER);
			query.setPassword(MYSQL_PASSWORD);
			query.setQuery("select * from Action");

			instances = query.retrieveInstances();
			instances.setClassIndex(14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializeAlgorithm() {
		try {
			Classifier classifier = AbstractClassifier.forName("weka.classifiers.meta.AdaBoostM1",
					new String[] { "-P", "100", "-I", "8", "-Q", "-S", "1", "-W", "weka.classifiers.rules.JRip", "--",
							"-N", "4.5956335694418815", "-E", "-O", "1" });

			Instances randData = new Instances(instances);
			Evaluation eval = new Evaluation(randData);
			eval.crossValidateModel(classifier, randData, 10, new Random(1));

			System.out.println();
			System.out.println("=== Setup ===");
			System.out.println("Classifier: " + classifier.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) classifier).getOptions()));
			System.out.println("Dataset: " + instances.relationName());
			System.out.println("Folds: " + 10);
			System.out.println("Seed: " + 1);
			System.out.println();
			System.out.println(eval.toSummaryString("=== " + 10 + "-fold Cross-validation ===", false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Instances getInstances() {
		return instances;
	}

	public void setInstances(Instances instances) {
		this.instances = instances;
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	
	
}
