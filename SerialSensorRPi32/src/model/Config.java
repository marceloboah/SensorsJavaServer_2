package model;

import java.sql.Timestamp;

public class Config {

	private int con_id;
	private String con_desc;
	private String con_par;
	private Timestamp con_gentime;
	private Timestamp con_modtime;
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Config(int con_id, String con_desc, String con_par, Timestamp con_gentime, Timestamp con_modtime) {
		super();
		this.con_id = con_id;
		this.con_desc = con_desc;
		this.con_par = con_par;
		this.con_gentime = con_gentime;
		this.con_modtime = con_modtime;
	}

	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getCon_desc() {
		return con_desc;
	}
	public void setCon_desc(String con_desc) {
		this.con_desc = con_desc;
	}
	public String getCon_par() {
		return con_par;
	}
	public void setCon_par(String con_par) {
		this.con_par = con_par;
	}
	public Timestamp getCon_gentime() {
		return con_gentime;
	}
	public void setCon_gentime(Timestamp con_gentime) {
		this.con_gentime = con_gentime;
	}
	public Timestamp getCon_modtime() {
		return con_modtime;
	}
	public void setCon_modtime(Timestamp con_modtime) {
		this.con_modtime = con_modtime;
	}
	
	
}
