package jp.saori.hitandblow.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class GameModel implements Serializable {
	private int digit;
	@NotEmpty(message="数字が入力されていません")
	private String number;
	private int[] answer;
	private int count;
	private List<TableModel> tableList;

	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int[] getAnswer() {
		return answer;
	}
	public int getAnswer(int i) {
		return answer[i];
	}
	public void setAnswer(int[] answer) {
		this.answer = answer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<TableModel> getTableList() {
		return tableList;
	}
	public void setTableList(List<TableModel> tableList) {
		this.tableList = tableList;
	}

	public String getAnswers() {
		String answer = Integer.valueOf(getAnswer(0)).toString();
		for (int i = 1; i < getAnswer().length; i++) {
			answer += getAnswer(i);
		}
		return answer;
	}
}
