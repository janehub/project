package com.next.kko.dtos;

import java.io.Serializable;

/**
 * <h1>육계일지 DTO</h1>
 * <p>설명
 * 	private String recordilji_seq;	                  육계일지작성SEQ
 * 	private String passcode;            	파스CODE
 * 	private int illyung;                    일령
 * 	private double hightemp;                최고온도(기상청)
 * 	private double lowtemp;                 최저온도(기상청)
 * 	private String recorddate;              작성날짜
 * 	private String recordtime;              작성시간
 * 	private String medicine;                약품
 * 	private String weathercon;              날씨상태
 * 	private String etc;                     시간별 비고
 * 	private int deathcount;                 폐사수
 * 	private double weight;                  중량
 * 	private double buildtemp;               사육장 온도
 * 	private String distinctdong;            동별구분  '/'로 구분 ex) 1/3/4
 * </p>                                    
 * @author 박지연
 * @since 2018.11.10
 * @version 1.0
 * @package com.next.kko.dtos
 */
public class RecordIljiDto implements Serializable{

	private static final long serialVersionUID = -4175001843976501365L;

	private String recordilji_seq;           //육계일지작성SEQ
	private String passcode;                 //파스CODE
	private int illyung;                     //일령
	private double hightemp;                 //최고온도(기상청)
	private double lowtemp;                  //최저온도(기상청)
	private String recorddate;               //작성날짜
	private String recordtime;               //작성시간
	private String medicine;                 //약품
	private String weathercon;               //날씨상태
	private String etc;                      //시간별 비고
	private int deathcount;                  //폐사수
	private double weight;                   //중량
	private double buildtemp;                //사육장 온도
	private String distinctdong;             //동별구분
	
	public RecordIljiDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordIljiDto(String recordilji_seq, String passcode, int illyung, double hightemp, double lowtemp,
			String recorddate, String recordtime, String medicine, String weathercon, String etc, int deathcount,
			double weight, double buildtemp, String distinctdong) {
		super();
		this.recordilji_seq = recordilji_seq;
		this.passcode = passcode;
		this.illyung = illyung;
		this.hightemp = hightemp;
		this.lowtemp = lowtemp;
		this.recorddate = recorddate;
		this.recordtime = recordtime;
		this.medicine = medicine;
		this.weathercon = weathercon;
		this.etc = etc;
		this.deathcount = deathcount;
		this.weight = weight;
		this.buildtemp = buildtemp;
		this.distinctdong = distinctdong;
	}

	public String getRecordilji_seq() {
		return recordilji_seq;
	}

	public void setRecordilji_seq(String recordilji_seq) {
		this.recordilji_seq = recordilji_seq;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public int getIllyung() {
		return illyung;
	}

	public void setIllyung(int illyung) {
		this.illyung = illyung;
	}

	public double getHightemp() {
		return hightemp;
	}

	public void setHightemp(double hightemp) {
		this.hightemp = hightemp;
	}

	public double getLowtemp() {
		return lowtemp;
	}

	public void setLowtemp(double lowtemp) {
		this.lowtemp = lowtemp;
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getWeathercon() {
		return weathercon;
	}

	public void setWeathercon(String weathercon) {
		this.weathercon = weathercon;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public int getDeathcount() {
		return deathcount;
	}

	public void setDeathcount(int deathcount) {
		this.deathcount = deathcount;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getBuildtemp() {
		return buildtemp;
	}

	public void setBuildtemp(double buildtemp) {
		this.buildtemp = buildtemp;
	}

	public String getDistinctdong() {
		return distinctdong;
	}

	public void setDistinctdong(String distinctdong) {
		this.distinctdong = distinctdong;
	}

	@Override
	public String toString() {
		return "RecordIljiDto [recordilji_seq=" + recordilji_seq + ", passcode=" + passcode + ", illyung=" + illyung
				+ ", hightemp=" + hightemp + ", lowtemp=" + lowtemp + ", recorddate=" + recorddate + ", recordtime="
				+ recordtime + ", medicine=" + medicine + ", weathercon=" + weathercon + ", etc=" + etc
				+ ", deathcount=" + deathcount + ", weight=" + weight + ", buildtemp=" + buildtemp + ", distinctdong="
				+ distinctdong + "]";
	}
	
	
}
