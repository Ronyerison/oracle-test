package br.ufpi.loes.oracleTest.core.machineLearning;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Evaluation;
import weka.core.Instances;

public class MachineLearningReport implements Serializable {

	private static final long serialVersionUID = 4926746817120766888L;

	private String algorithmName;
	private String evaluation;
	private String classifierOptions;
	private Integer folds;
	private double[][] confusionMatrix;
	private Double kappa;
	private Double errorrate;
	private Double meanAbsoluteError;
	private Double pctCorrect;
	private Double pctIncorrect;
	private Double numCorrect;
	private Double numIncorrect;
	private Map<String, ClassMeasurementReport> classMeasurements;

	public MachineLearningReport() {
		this.classMeasurements = new HashMap<String, ClassMeasurementReport>();
	}

	public MachineLearningReport(String algorithmName, String evaluation, String classifierOptions, Integer folds,
			Integer seed, double[][] confusionMatrix, Double kappa, Double errorrate, Double meanAbsoluteError,
			Double pctCorrect, Double pctIncorrect, Double numCorrect, Double numIncorrect) {
		super();
		this.algorithmName = algorithmName;
		this.evaluation = evaluation;
		this.classifierOptions = classifierOptions;
		this.folds = folds;
		this.confusionMatrix = confusionMatrix;
		this.kappa = kappa;
		this.errorrate = errorrate;
		this.meanAbsoluteError = meanAbsoluteError;
		this.pctCorrect = pctCorrect;
		this.pctIncorrect = pctIncorrect;
		this.numCorrect = numCorrect;
		this.numIncorrect = numIncorrect;
		this.classMeasurements = new HashMap<String, ClassMeasurementReport>();
	}

	public MachineLearningReport(String algorithmName, String evaluation, String classifierOptions, Integer folds) {
		super();
		this.algorithmName = algorithmName;
		this.evaluation = evaluation;
		this.classifierOptions = classifierOptions;
		this.folds = folds;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getClassifierOptions() {
		return classifierOptions;
	}

	public void setClassifierOptions(String classifierOptions) {
		this.classifierOptions = classifierOptions;
	}

	public Integer getFolds() {
		return folds;
	}

	public void setFolds(Integer folds) {
		this.folds = folds;
	}

	public double[][] getConfusionMatrix() {
		return confusionMatrix;
	}

	public void setConfusionMatrix(double[][] confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	public Double getKappa() {
		return kappa;
	}

	public void setKappa(Double kappa) {
		this.kappa = kappa;
	}

	public Double getErrorrate() {
		return errorrate;
	}

	public void setErrorrate(Double errorrate) {
		this.errorrate = errorrate;
	}

	public Double getMeanAbsoluteError() {
		return meanAbsoluteError;
	}

	public void setMeanAbsoluteError(Double meanAbsoluteError) {
		this.meanAbsoluteError = meanAbsoluteError;
	}

	public Double getPctCorrect() {
		return pctCorrect;
	}

	public void setPctCorrect(Double pctCorrect) {
		this.pctCorrect = pctCorrect;
	}

	public Double getPctIncorrect() {
		return pctIncorrect;
	}

	public void setPctIncorrect(Double pctIncorrect) {
		this.pctIncorrect = pctIncorrect;
	}

	public Double getNumCorrect() {
		return numCorrect;
	}

	public void setNumCorrect(Double numCorrect) {
		this.numCorrect = numCorrect;
	}

	public Double getNumIncorrect() {
		return numIncorrect;
	}

	public void setNumIncorrect(Double numIncorrect) {
		this.numIncorrect = numIncorrect;
	}

	public void addClassMeasurements(Instances instances, Evaluation evaluation) {
		int numClasses = instances.attribute(instances.classIndex()).numValues();
		for (int i = 0; i < numClasses; i++) {
			ClassMeasurementReport measurement = new ClassMeasurementReport(evaluation.precision(i),
					evaluation.fMeasure(i), evaluation.areaUnderPRC(i), evaluation.recall(i), evaluation.falseNegativeRate(i), evaluation.falsePositiveRate(i),
					evaluation.numFalseNegatives(i), evaluation.numFalsePositives(i), evaluation.trueNegativeRate(i), evaluation.truePositiveRate(i), evaluation.numTrueNegatives(i),
					evaluation.numTruePositives(i));
			this.classMeasurements.put(instances.attribute(instances.classIndex()).value(i), measurement);
		}

	}

	@Override
	public String toString() {
		return "MachineLearningReport [algorithmName=" + algorithmName + ", classifierOptions=" + classifierOptions
				+ ", folds=" + folds + ", confusionMatrix=" + Arrays.toString(confusionMatrix) + ", kappa=" + kappa
				+ ", errorrate=" + errorrate + ", meanAbsoluteError=" + meanAbsoluteError + ", pctCorrect=" + pctCorrect
				+ ", pctIncorrect=" + pctIncorrect + ", numCorrect=" + numCorrect + ", numIncorrect=" + numIncorrect
				+ ", classMeasurements=" + classMeasurements + "]";
	}

	
	
	
}
