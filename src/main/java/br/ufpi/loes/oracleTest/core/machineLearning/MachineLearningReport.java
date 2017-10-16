package br.ufpi.loes.oracleTest.core.machineLearning;

import java.io.Serializable;

public class MachineLearningReport implements Serializable{

	private static final long serialVersionUID = 4926746817120766888L;

	private String algorithmName;
	private String evaluation;
	private String classifierOptions;
	private Integer folds;
	private Integer seed;
	
	public MachineLearningReport() {

	}
	
	public MachineLearningReport(String algorithmName, String evaluation, String classifierOptions, Integer folds,
			Integer seed) {
		super();
		this.algorithmName = algorithmName;
		this.evaluation = evaluation;
		this.classifierOptions = classifierOptions;
		this.folds = folds;
		this.seed = seed;
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

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}

	@Override
	public String toString() {
		return "MachineLearningReport [algorithmName=" + algorithmName + ", evaluation=" + evaluation
				+ ", classifierOptions=" + classifierOptions + ", folds=" + folds + ", seed=" + seed + "]";
	}
	
	
}
