package com.natergy.natergyh5.entity;
/**
 * 用户bean
 * @author 四爷
 *
 */
public class User {
	private String uname;
	private String pwd;

	@Override
	public String toString() {
		return "User [uname=" + uname + ", pwd=" + pwd + "]";
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
