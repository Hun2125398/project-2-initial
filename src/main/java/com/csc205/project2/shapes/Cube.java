package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4 - September 28, 2025
 * Generation Date: September 28, 2025
 * 
 * Original Prompt:
 * "Generate a Cube class that extends Shape3D with properties: sideLength. Include proper constructors with validation, implement abstract calculation methods, override toString() with shape-specific formatting."
 * 
 * Follow-up Prompts (if any):
 * (None required)
 * 
 * Manual Modifications:
 * (To be filled in by developer if changes are made)
 * 
 * Formula Verification:
 * - Volume formula verified against: V = s³
 * - Surface area formula verified against: SA = 6s²
 */
public class Cube extends Shape3D {
    private double sideLength;
    
    /**
     * Default constructor
     */
    public Cube() {
        super("Cube", "Unknown Color");
        this.sideLength = 1.0;
    }
    
    /**
     * Constructor with side length
     * @param sideLength The length of each side of the cube
     * @throws IllegalArgumentException if sideLength is negative or zero
     */
    public Cube(double sideLength) {
        super("Cube", "Unknown Color");
        validateSideLength(sideLength);
        this.sideLength = sideLength;
    }
    
    /**
     * Constructor with name, color, and side length
     * @param name The name of the cube
     * @param color The color of the cube
     * @param sideLength The length of each side of the cube
     * @throws IllegalArgumentException if sideLength is negative or zero
     */
    public Cube(String name, String color, double sideLength) {
        super(name, color);
        validateSideLength(sideLength);
        this.sideLength = sideLength;
    }
    
    /**
     * Validates side length input
     * @param sideLength The side length to validate
     * @throws IllegalArgumentException if sideLength is negative or zero
     */
    private void validateSideLength(double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be positive and greater than zero");
        }
    }
    
    /**
     * Gets the side length of the cube
     * @return The side length
     */
    public double getSideLength() {
        return sideLength;
    }
    
    /**
     * Sets the side length of the cube
     * @param sideLength The new side length
     * @throws IllegalArgumentException if sideLength is negative or zero
     */
    public void setSideLength(double sideLength) {
        validateSideLength(sideLength);
        this.sideLength = sideLength;
    }
    
    /**
     * Calculates the volume of the cube
     * Formula: V = s³
     * @return The volume
     */
    @Override
    protected double calculateVolume() {
        return Math.pow(sideLength, 3);
    }
    
    /**
     * Calculates the surface area of the cube
     * Formula: SA = 6s²
     * @return The surface area
     */
    @Override
    protected double calculateSurfaceArea() {
        return 6 * Math.pow(sideLength, 2);
    }
    
    /**
     * Returns a string representation of the cube
     * @return Formatted string with cube information
     */
    @Override
    public String toString() {
        return String.format("%s [Color: %s, Side Length: %.2f, Volume: %.2f, Surface Area: %.2f]", 
                           getName(), getColor(), sideLength, getVolume(), getSurfaceArea());
    }
    
    /**
     * Gets the diagonal of a face of the cube
     * @return The face diagonal (s√2)
     */
    public double getFaceDiagonal() {
        return sideLength * Math.sqrt(2);
    }
    
    /**
     * Gets the space diagonal of the cube
     * @return The space diagonal (s√3)
     */
    public double getSpaceDiagonal() {
        return sideLength * Math.sqrt(3);
    }
}