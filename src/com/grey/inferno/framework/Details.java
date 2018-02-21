package com.grey.inferno.framework;

import java.io.Serializable;

public class Details implements Comparable<Details>, Serializable, CharSequence {

	private static final long serialVersionUID = 1L;
	public String name;
	public int score;


	public Details(String name, int score) {
		this.name = name;
		this.score = score;


		//System.out.println("CALLED");
	}

	public Details(Details det) {
		this.name = det.name;
		this.score = det.score;
	}

	@Override
	public int compareTo(Details o) {
		int comparedScore = o.score;
		if (this.score > comparedScore) {
			return 1;
		} else if (this.score == comparedScore) {
			return 0;
		} else {
			return -1;
		}
	}

	public String toString() {
		return score +" - " + name;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
}
