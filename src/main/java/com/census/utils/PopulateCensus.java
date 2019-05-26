package com.census.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.census.bean.Census;

public class PopulateCensus {
	public static ArrayList<Census> storeCensus(final ArrayList<String> list) {
		final ArrayList<Census> populated = new ArrayList<Census>();
		for (int i = 0; i < list.size(); i++) {
			final Census loading = new Census();
			final String Line = (String) list.get(i);
			final StringTokenizer stoken = new StringTokenizer(Line, ",");

			while (stoken.hasMoreTokens()) {
				// Creating instance for the Memory class
				@SuppressWarnings("unused")
				final String SerialNo = stoken.nextToken();
				final String State = stoken.nextToken();
				// Appending the tokens if district having two or more elements
				final String dis = stoken.nextToken();
				final StringBuffer sbuffer = new StringBuffer();
				sbuffer.append(dis);
				while (stoken.countTokens() > 16) {
					final char cha = ',';
					sbuffer.append(cha).append(stoken.nextToken());
				}
				final String District = sbuffer.toString();
				String res = null;
				String res1 = null;
				if (District.startsWith("\"")) {
					res = District.substring(1, District.length());
					res1 = res.replaceAll("District ", "");
					res1 = res1.replaceAll("\"", "");
					res1 = res1.replaceAll(",", "_");

				} else {
					res = District;
					res1 = res.replaceAll("District ", "");
					res1 = res1.replaceAll(",", "_");
				}
				loading.setStatename(State);
				loading.setDistrictName(res1);
				final Long axz = Long.parseLong(stoken.nextToken());
				loading.setPersons(axz);
				loading.setMale(stoken.nextToken());
				loading.setFemale(stoken.nextToken());
				final String noh = stoken.nextToken();
				if (!noh.equals("NA")) {
					final Long abc = Long.parseLong(noh);
					loading.setNumberofhouseholds(abc);
				} else {
					final Long abc = (long) 0.0;
					loading.setNumberofhouseholds(abc);
				}
				loading.setRatio(stoken.nextToken());
				loading.setPersonsLiterate(stoken.nextToken());
				loading.setMalesliterate(stoken.nextToken());
				loading.setFemalsesliterate(stoken.nextToken());
				loading.setPersonsliteracyRate(stoken.nextToken());
				loading.setMalesLiteracyRate(stoken.nextToken());
				loading.setFemalesLiteracyRate(stoken.nextToken());
				final String educ = stoken.nextToken();
				if (!educ.equals("NA")) {
					final Long edu = Long.parseLong(educ);
					loading.setEducated(edu);
				} else {
					final Long edk = (long) 0.0;
					loading.setEducated(edk);
				}

				final Long wal = Long.parseLong(stoken.nextToken());
				loading.setWorkers(wal);
				loading.setMainworkers(stoken.nextToken());
				loading.setMarginalworkers(stoken.nextToken());
				loading.setNonworkers(stoken.nextToken());
			}
			populated.add(loading);
		}

		return populated;

	}
}