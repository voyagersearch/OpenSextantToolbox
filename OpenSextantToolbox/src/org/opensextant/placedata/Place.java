/**
 Copyright 2009-2013 The MITRE Corporation.
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 * **************************************************************************
 *                          NOTICE
 * This software was produced for the U. S. Government under Contract No.
 * W15P7T-12-C-F600, and is subject to the Rights in Noncommercial Computer
 * Software and Noncommercial Computer Software Documentation Clause
 * 252.227-7014 (JUN 1995)
 *
 * (c) 2012 The MITRE Corporation. All Rights Reserved.
 * **************************************************************************
 **/
package org.opensextant.placedata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Place represents a named geographic location, a "place". It contains basic information about that place, such as
 * its name, its geographic location, the country it is part of or located in, what type of place it is (e.g. city,
 * river, province) and the original source of this information. These reflect the data traditionally found in
 * gazetteers.
 */
public class Place implements Comparable<Object>, Serializable {
  private static final long serialVersionUID = 2389068012345L;

  // The geospatial data
  private String countryCode; // ISO2 code
  // private String countryCodeFIPS; // FIPS code
  // private String countryCodeISO3; // ISO3 code
  private String admin1;
  private String admin2;
  // private String admin3;
  // private String admin4;
  // private String admin5;

  // what type of place this is
  private String featureClass;
  private String featureCode;

  // its location as a point
  private Geocoord geocoord = new Geocoord();

  private String source;
  private String sourceFeatureID;
  private String placeID;

  // all the names for this Place
  private List<PlaceName> placeNames = new ArrayList<PlaceName>();

  // The ID bias is a measure of the a priori likelihood that a mention of this name refers to this particular place.
  private double idBias;

  // values used for nameType
  public static final String ABBREV_TYPE = "abbrev";
  public static final String CODE_TYPE = "code";
  public static final String NAME_TYPE = "name";

  // construct an empty place
  public Place() {
  }
  /*
   * // construct a place with just a name and ID public Place(String id, String name) { setPlaceID(id);
   * setPlaceName(name); }
   */
  @Override
  public String toString() {
    String output = "";
    if (this.placeNames.get(0) != null) {
      output = this.placeNames.get(0).getPlaceName() + " (" + this.placeNames.get(0).getExpandedPlaceName() + ")" + "("
          + this.getAdmin1() + "," + this.countryCode + "," + this.featureCode + ")";
    } else {
      output = this.placeNames.get(0).getPlaceName() + " (" + this.getAdmin1() + "," + this.countryCode + ","
          + this.featureCode + ")";
    }

    return output;
  }

  // two Places with the same PlaceID are the same "place"
  // two Places with different PlaceIDs ARE PROBABLY different "places"
  @Override
  public int compareTo(Object other) {
    if (!(other instanceof Place)) {
      return 0;
    }
    Place tmp = (Place) other;
    return this.placeID.compareTo(tmp.placeID);
  }

  /**
   * Is this Place a Country?
   * @return - true if this is a country or "country-like" place
   */
  public boolean isACountry() {
    return featureCode.startsWith("PCL");
  }

  /**
   * Is this Place a State or Province?
   * @return - true if this is a State, Province or other first level admin area
   */
  public boolean isAnAdmin1() {
    return "ADM1".equalsIgnoreCase(featureCode);
  }

  /**
   * Is this Place a National Capital?
   * @return - true if this is a a national Capital area
   */
  public boolean isNationalCapital() {
    return "PPLC".equalsIgnoreCase(featureCode);
  }

  /**
   * Is this name an abbreviation or code?
   * @return - true if this name is an abbreviation or code
   */
  public boolean isAbbreviation(String name) {

    for (PlaceName n : this.placeNames) {
      String typ = n.getNameType();
      if ((typ.equalsIgnoreCase(ABBREV_TYPE) || typ.equalsIgnoreCase(CODE_TYPE))
          && n.getPlaceName().equalsIgnoreCase(name)) {
        return true;
      }
    }

    return false;
  }

  // The getters and setters

  public String getPlaceName() {
    return this.placeNames.get(0).getPlaceName();
  }

  public String getExpandedPlaceName() {
    return this.placeNames.get(0).getExpandedPlaceName();
  }

  public String getNameType() {
    return this.placeNames.get(0).getNameType();
  }

  public String getNameTypeSystem() {
    return this.placeNames.get(0).getNameTypeSystem();
  }

  public void addPlaceName(PlaceName name) {
    this.placeNames.add(name);
  }

  public List<String> getNames() {
    List<String> names = new ArrayList<String>();
    for (PlaceName n : this.getPlaceNames()) {
      names.add(n.getPlaceName());
    }
    return names;
  }

  public List<String> getNameTypes() {
    List<String> nameTypes = new ArrayList<String>();
    for (PlaceName n : this.getPlaceNames()) {
      nameTypes.add(n.getNameType());
    }
    return nameTypes;
  }

  public Double getNameBias(String name) {
    Double bias = 0.0;
    for (PlaceName n : this.getPlaceNames()) {
      if (n.getPlaceName().equalsIgnoreCase(name)) {
        bias = n.getNameBias();
      }

    }
    return bias;
  }

  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }
  /**
   * @param countryCode
   *          the countryCode to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  /**
   * @return the admin1
   */
  public String getAdmin1() {
    return admin1;
  }
  /**
   * @param admin1
   *          the admin1 to set
   */
  public void setAdmin1(String admin1) {
    this.admin1 = admin1;
  }
  /**
   * @return the admin2
   */
  public String getAdmin2() {
    return admin2;
  }
  /**
   * @param admin2
   *          the admin2 to set
   */
  public void setAdmin2(String admin2) {
    this.admin2 = admin2;
  }
  /**
   * @return the featureClass
   */
  public String getFeatureClass() {
    return featureClass;
  }
  /**
   * @param featureClass
   *          the featureClass to set
   */
  public void setFeatureClass(String featureClass) {
    this.featureClass = featureClass;
  }
  /**
   * @return the featureCode
   */
  public String getFeatureCode() {
    return featureCode;
  }
  /**
   * @param featureCode
   *          the featureCode to set
   */
  public void setFeatureCode(String featureCode) {
    this.featureCode = featureCode;
  }
  /**
   * @return the geocoord
   */
  public Geocoord getGeocoord() {
    return geocoord;
  }
  /**
   * @param geocoord
   *          the geocoord to set
   */
  public void setGeocoord(Geocoord geocoord) {
    this.geocoord = geocoord;
  }

  public Double getLatitude() {
    return this.geocoord.getLatitude();
  }

  public Double getLongitude() {
    return this.geocoord.getLongitude();
  }

  public void setLatitude(Double lat) {
    this.geocoord.setLatitude(lat);
  }

  public void setLongitude(Double lon) {
    this.geocoord.setLongitude(lon);
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }
  /**
   * @param source
   *          the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }
  /**
   * @return the sourceFeatureID
   */
  public String getSourceFeatureID() {
    return sourceFeatureID;
  }
  /**
   * @param sourceFeatureID
   *          the sourceFeatureID to set
   */
  public void setSourceFeatureID(String sourceFeatureID) {
    this.sourceFeatureID = sourceFeatureID;
  }
  /**
   * @return the placeID
   */
  public String getPlaceID() {
    return placeID;
  }
  /**
   * @param placeID
   *          the placeID to set
   */
  public void setPlaceID(String placeID) {
    this.placeID = placeID;
  }
  /**
   * @return the placeNames
   */
  public List<PlaceName> getPlaceNames() {
    return placeNames;
  }
  /**
   * @param placeNames
   *          the placeNames to set
   */
  public void setPlaceNames(List<PlaceName> placeNames) {
    this.placeNames = placeNames;
  }
  /**
   * @return the idBias
   */
  public double getIdBias() {
    return idBias;
  }
  /**
   * @param idBias
   *          the idBias to set
   */
  public void setIdBias(double idBias) {
    this.idBias = idBias;
  }

}
