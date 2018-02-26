package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClassMeasurementReport implements Serializable{
	
	private static final long serialVersionUID = -8310473962793200876L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String className;
	private Double precision;
	private Double fMeasure;
	private Double areaUnderROC;
	private Double recall;
	private Double falseNegativeRate;
	private Double falsePositiveRate;
	private Double numFalseNegative;
	private Double numFalsePositive;
	private Double trueNegativeRate;
	private Double truePositiveRate;
	private Double numTrueNegative;
	private Double numTruePositive;
	
	@ManyToOne
	private MachineLearningReport report;
	
	public ClassMeasurementReport() {
	}
	
	public ClassMeasurementReport(Double precision, Double fMeasure, Double areaUnderROC, Double recall,
			Double falseNegativeRate, Double falsePositiveRate, Double numFalseNegative, Double numFalsePositive,
			Double trueNegativeRate, Double truePositiveRate, Double numTrueNegative, Double numTruePositive) {
		super();
		this.precision = precision;
		this.fMeasure = fMeasure;
		this.areaUnderROC = areaUnderROC;
		this.recall = recall;
		this.falseNegativeRate = falseNegativeRate;
		this.falsePositiveRate = falsePositiveRate;
		this.numFalseNegative = numFalseNegative;
		this.numFalsePositive = numFalsePositive;
		this.trueNegativeRate = trueNegativeRate;
		this.truePositiveRate = truePositiveRate;
		this.numTrueNegative = numTrueNegative;
		this.numTruePositive = numTruePositive;
	}

	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
		this.precision = precision;
	}
	public Double getfMeasure() {
		return fMeasure;
	}
	public void setfMeasure(Double fMeasure) {
		this.fMeasure = fMeasure;
	}
	public Double getAreaUnderROC() {
		return areaUnderROC;
	}
	public void setAreaUnderROC(Double areaUnderROC) {
		this.areaUnderROC = areaUnderROC;
	}
	public Double getRecall() {
		return recall;
	}
	public void setRecall(Double recall) {
		this.recall = recall;
	}
	public Double getFalseNegativeRate() {
		return falseNegativeRate;
	}
	public void setFalseNegativeRate(Double falseNegativeRate) {
		this.falseNegativeRate = falseNegativeRate;
	}
	public Double getFalsePositiveRate() {
		return falsePositiveRate;
	}
	public void setFalsePositiveRate(Double falsePositiveRate) {
		this.falsePositiveRate = falsePositiveRate;
	}
	public Double getNumFalseNegative() {
		return numFalseNegative;
	}
	public void setNumFalseNegative(Double numFalseNegative) {
		this.numFalseNegative = numFalseNegative;
	}
	public Double getNumFalsePositive() {
		return numFalsePositive;
	}
	public void setNumFalsePositive(Double numFalsePositive) {
		this.numFalsePositive = numFalsePositive;
	}
	public Double getTrueNegativeRate() {
		return trueNegativeRate;
	}
	public void setTrueNegativeRate(Double trueNegativeRate) {
		this.trueNegativeRate = trueNegativeRate;
	}
	public Double getTruePositiveRate() {
		return truePositiveRate;
	}
	public void setTruePositiveRate(Double truePositiveRate) {
		this.truePositiveRate = truePositiveRate;
	}
	public Double getNumTrueNegative() {
		return numTrueNegative;
	}
	public void setNumTrueNegative(Double numTrueNegative) {
		this.numTrueNegative = numTrueNegative;
	}
	public Double getNumTruePositive() {
		return numTruePositive;
	}
	public void setNumTruePositive(Double numTruePositive) {
		this.numTruePositive = numTruePositive;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public MachineLearningReport getReport() {
		return report;
	}

	public void setReport(MachineLearningReport report) {
		this.report = report;
	}


	
	
	@Override
	public String toString() {
		return "ClassMeasurementReport [precision=" + precision + ", fMeasure=" + fMeasure + ", areaUnderROC="
				+ areaUnderROC + ", recall=" + recall + ", falseNegativeRate=" + falseNegativeRate
				+ ", falsePositiveRate=" + falsePositiveRate + ", numFalseNegative=" + numFalseNegative
				+ ", numFalsePositive=" + numFalsePositive + ", trueNegativeRate=" + trueNegativeRate
				+ ", truePositiveRate=" + truePositiveRate + ", numTrueNegative=" + numTrueNegative
				+ ", numTruePositive=" + numTruePositive + "]";
	}
	
	
	
}
