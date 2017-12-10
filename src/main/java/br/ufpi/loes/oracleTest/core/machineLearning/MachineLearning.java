/**
 * 
 */
package br.ufpi.loes.oracleTest.core.machineLearning;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

import br.ufpi.loes.oracleTest.common.util.StringUtilConverter;
import br.ufpi.loes.oracleTest.web.model.MachineLearningReport;
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
public class MachineLearning implements Serializable {

	private static final long serialVersionUID = 2723293435964284837L;

	private Instances instances;
	private Classifier classifier;
	private MachineLearningReport report;
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PASSWORD = "root";

	public MachineLearning() {

	}

	public void inicializeInstances(String applicationName) {
		try {

			String sql = "SELECT a.sActiontype, a.sTag, a.sTagIndex, a.sUrl, a.sXPath, a.sOracleVisibleElements, a.sOracleUrl, a.sOracleveredict  FROM Action a WHERE a.sClient LIKE '%" + applicationName + "%'";
			File file = new File(getClass().getClassLoader().getResource("DatabaseUtils.props").toURI());
			InstanceQuery query = new InstanceQuery();
			query.setCustomPropsFile(file);
			query.setUsername(MYSQL_USER);
			query.setPassword(MYSQL_PASSWORD);
			query.setQuery(sql);

			instances = query.retrieveInstances();
			instances.setClassIndex(7);
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
			
			this.report = new MachineLearningReport();
			this.report.setAlgorithmName(classifier.getClass().getName());
			this.report.setEvaluation(eval.toSummaryString(10 + "-fold Cross-validation", false));
			this.report.setClassifierOptions(Utils.joinOptions(((AbstractClassifier) classifier).getOptions()));
			this.report.setFolds(10);
			this.report.setConfusionMatrix(eval.confusionMatrix());
			this.report.setErrorrate(eval.errorRate());
			this.report.setKappa(eval.kappa());
			this.report.setMeanAbsoluteError(eval.meanAbsoluteError());
			this.report.setNumCorrect(eval.correct());
			this.report.setNumIncorrect(eval.incorrect());
			this.report.setPctCorrect(eval.pctCorrect());
			this.report.setPctIncorrect(eval.pctIncorrect());
			this.report.addClassMeasurements(randData, eval);
			this.report.setConfusionMatrixString(StringUtilConverter.arrayToString(this.report.getConfusionMatrix()));
//			randData.attribute(randData.classIndex()).enumerateValues();

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

	public MachineLearningReport getReport() {
		return report;
	}

	public void setReport(MachineLearningReport report) {
		this.report = report;
	}
	
	

}
