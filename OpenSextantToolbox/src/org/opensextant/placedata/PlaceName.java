package org.opensextant.placedata;

public class PlaceName {
  private String placeName;
  private String expandedPlaceName;
  private String nameType;
  private String nameTypeSystem;
  private String sourceNameID;
  private String source;

  // The name bias is a measure of the a priori likelihood that a mention of this place's name actually refers to a
  // place.
  private double nameBias;

  public PlaceName() {
  }

  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public String getExpandedPlaceName() {
    return expandedPlaceName;
  }

  public void setExpandedPlaceName(String expandedPlaceName) {
    this.expandedPlaceName = expandedPlaceName;
  }

  public String getNameType() {
    return nameType;
  }

  public void setNameType(String nameType) {
    this.nameType = nameType;
  }

  public String getNameTypeSystem() {
    return nameTypeSystem;
  }

  public void setNameTypeSystem(String nameTypeSystem) {
    this.nameTypeSystem = nameTypeSystem;
  }

  public String getSourceNameID() {
    return sourceNameID;
  }

  public void setSourceNameID(String sourceNameID) {
    this.sourceNameID = sourceNameID;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public double getNameBias() {
    return nameBias;
  }

  public void setNameBias(double nameBias) {
    this.nameBias = nameBias;
  }
}