package com.csc205.project2.shapes;

/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4 - September 28, 2025
 * Generation Date: September 28, 2025
 * 
 * Original Prompt:
 * "Generate a Cone class that extends Shape3D with properties: radius, height. Include proper constructors with validation, implement abstract calculation methods, override toString() with shape-specific formatting."
 * 
 * Follow-up Prompts (if any):
 * (None required)
 * 
 * Manual Modifications:
 * (To be filled in by developer if changes are made)
 * 
 * Formula Verification:
 * - Volume formula verified against: V = (1/3)πr²h
 * - Surface area formula verified against: SA = πr² + πr√(r² + h²)
 */
public class Cone extends Shape3D {
    private double radius;
    private double height;
    
    /**
     * Default constructor
     */
    public Cone() {
        super("Cone", "Unknown Color");
        this.radius = 1.0;
        this.height = 1.0;
    }
    
    /**
     * Constructor with radius and height
     * @param radius The radius of the cone base
     * @param height The height of the cone
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    public Cone(double radius, double height) {
        super("Cone", "Unknown Color");
        validateDimensions(radius, height);
        this.radius = radius;
        this.height = height;
    }
    
    /**
     * Constructor with name, color, radius, and height
     * @param name The name of the cone
     * @param color The color of the cone
     * @param radius The radius of the cone base
     * @param height The height of the cone
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    public Cone(String name, String color, double radius, double height) {
        super(name, color);
        validateDimensions(radius, height);
        this.radius = radius;
        this.height = height;
    }
    
    /**
     * Validates radius and height inputs
     * @param radius The radius to validate
     * @param height The height to validate
     * @throws IllegalArgumentException if radius or height is negative or zero
     */
    private void validateDimensions(double radius, double height) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and greater than zero");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive and greater than zero");
        }
    }
    
    /**
     * Gets the radius of the cone
     * @return The radius
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * Sets the radius of the cone
     * @param radius The new radius
     * @throws IllegalArgumentException if radius is negative or zero
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and greater than zero");
        }
        this.radius = radius;
    }
    
    /**
     * Gets the height of the cone
     * @return The height
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Sets the height of the cone
     * @param height The new height
     * @throws IllegalArgumentException if height is negative or zero
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive and greater than zero");
        }
        this.height = height;
    }
    
    /**
     * Calculates the volume of the cone
     * Formula: V = (1/3)πr²h
     * @return The volume
     */
    @Override
    protected double calculateVolume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
    }
    
    /**
     * Calculates the surface area of the cone
     * Formula: SA = πr² + πr√(r² + h²) (base area + lateral surface area)
     * @return The surface area
     */
    @Override
    protected double calculateSurfaceArea() {
        double baseArea = Math.PI * Math.pow(radius, 2);
        double slantHeight = getSlantHeight();
        double lateralArea = Math.PI * radius * slantHeight;
        return baseArea + lateralArea;
    }
    
    /**
     * Returns a string representation of the cone
     * @return Formatted string with cone information
     */
    @Override
    public String toString() {
        return String.format("%s [Color: %s, Radius: %.2f, Height: %.2f, Volume: %.2f, Surface Area: %.2f]", 
                           getName(), getColor(), radius, height, getVolume(), getSurfaceArea());
    }
    
    /**
     * Gets the slant height of the cone
     * Formula: l = √(r² + h²)
     * @return The slant height
     */
    public double getSlantHeight() {
        return Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
    }
    
    /**
     * Gets the diameter of the cone base
     * @return The diameter (2 * radius)
     */
    public double getDiameter() {
        return 2 * radius;
    }
    
    /**
     * Gets the base area of the cone
     * @return The base area (πr²)
     */
    public double getBaseArea() {
        return Math.PI * Math.pow(radius, 2);
    }
    
    /**
     * Gets the lateral (curved) surface area of the cone
     * @return The lateral surface area (πrl where l is slant height)
     */
    public double getLateralSurfaceArea() {
        return Math.PI * radius * getSlantHeight();
    }
}