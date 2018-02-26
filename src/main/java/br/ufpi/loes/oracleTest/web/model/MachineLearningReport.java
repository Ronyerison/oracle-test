package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import weka.classifiers.Evaluation;
import weka.core.Instances;

@Entity
public class MachineLearningReport implements Serializable {

	private static final long serialVersionUID = 4926746817120766888L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String algorithmName;
	@Transient
	private String evaluation;
	private String classifierOptions;
	private Integer folds;
	@Transient
	private double[][] confusionMatrix;
	private String confusionMatrixString;
	private Double kappa;
	private Double errorrate;
	private Double meanAbsoluteError;
	private Double pctCorrect;
	private Double pctIncorrect;
	private Double numCorrect;
	private Double numIncorrect;
	
	
	
//	@ElementCollection
//	@CollectionTable
//	@MapKeyColumn(name="class_name")
	@OneToMany(mappedBy="report", cascade=CascadeType.ALL)
	private List<ClassMeasurementReport> classMeasurements;
	
	@ManyToOne
	private Application application;

	public MachineLearningReport() {
		this.classMeasurements = new ArrayList<ClassMeasurementReport>();
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
		this.classMeasurements = new ArrayList<ClassMeasurementReport>();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ClassMeasurementReport> getClassMeasurements() {
		return classMeasurements;
	}

	public void setClassMeasurements(List<ClassMeasurementReport> classMeasurements) {
		this.classMeasurements = classMeasurements;
	}
	
	public String getConfusionMatrixString() {
		return confusionMatrixString;
	}

	public void setConfusionMatrixString(String confusionMatrixString) {
		this.confusionMatrixString = confusionMatrixString;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	public void addClassMeasurements(Instances instances, Evaluation evaluation) {
		int numClasses = instances.attribute(instances.classIndex()).numValues();
		for (int i = 0; i < numClasses; i++) {
			ClassMeasurementReport measurement = new ClassMeasurementReport(evaluation.precision(i),
					evaluation.fMeasure(i), evaluation.areaUnderPRC(i), evaluation.recall(i), evaluation.falseNegativeRate(i), evaluation.falsePositiveRate(i),
					evaluation.numFalseNegatives(i), evaluation.numFalsePositives(i), evaluation.trueNegativeRate(i), evaluation.truePositiveRate(i), evaluation.numTrueNegatives(i),
					evaluation.numTruePositives(i));
			measurement.setClassName(instances.attribute(instances.classIndex()).value(i));
			
			this.classMeasurements.add(measurement);
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
