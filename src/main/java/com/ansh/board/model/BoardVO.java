package com.ansh.board.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BoardVO {
	@Id
	@GeneratedValue
	private int idx;
	@Column
	private String user_name;
	@Column
	private String subject;
	@Column
	private String text;
	@Column
	private int readCount;
	@Column
	private LocalDate reg_datetime;
	@Column
	private char del_flag;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public LocalDate getReg_datetime() {
		return reg_datetime;
	}
	public void setReg_datetime(LocalDate reg_datetime) {
		this.reg_datetime = reg_datetime;
	}
	public char getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(char del_flag) {
		this.del_flag = del_flag;
	}
}